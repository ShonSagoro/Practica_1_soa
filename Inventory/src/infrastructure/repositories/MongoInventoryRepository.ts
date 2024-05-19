import { Inventory } from './../../domain/model/Inventory';
import { Collection } from "mongodb";
import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { connect } from "../../database/mongodb";


export class MongoInventoryRepository implements IInventoryRepository {
    private collection!: Collection | any;

    constructor() {
        this.initializeCollection();
    }

    async decreaseStock(uuid: string, stock: number): Promise<Inventory | null> {
        try{
            let order = await this.findByUuid(uuid);
            if(order){
                order.stock -= stock;
                this.collection.updateOne({ uuid: order.uuid }, { $set: { stock: order.stock } });
                return order;
            }else{
                return Promise.resolve(null);
            }
        }catch(error){
            return Promise.resolve(null);
        }
        
    }

    async findByUuid(uuid: string): Promise<Inventory | null> {
        try{
            const result = await this.collection.findOne({ uuid: uuid });
            if(result){
                let inventory = new Inventory(result.name, result.price, result.stock);
                inventory.uuid = result.uuid;
                return inventory;
            }
            return Promise.resolve(null);
        }catch (error) {
            return Promise.resolve(null);
        }
    }
    
    async create(inventory: Inventory): Promise<Inventory | null> {
        try {
            this.collection.insertOne(inventory);
            return Promise.resolve(inventory);
        } catch (error) {
            console.log(error);
            return Promise.resolve(null);
        }
    }

    async delete(uuid: string): Promise<boolean> {
        try {
            this.collection.deleteOne({ uuid: uuid });
            return Promise.resolve(true);
        } catch (error) {
            return Promise.resolve(false);
        }
    }

    async list(): Promise<Inventory[] | null> {
        try {
            const result = await this.collection.find().toArray();
            if (result) {
                return result.map((element: any) => {
                    let inventory = new Inventory(element.name, element.price, element.stock);
                    inventory.uuid = element.uuid;
                    return inventory;
                });
            }
            return Promise.resolve(null);
        } catch (error) {
            return Promise.resolve(null);
        }
    }

   
    private async initializeCollection(): Promise<void> {
        this.collection = await connect("inventories");
    }
}