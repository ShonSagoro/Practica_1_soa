import { Collection } from "mongodb";
import { InventoryInterface } from "../../domain/port/InventoryInterface";
import { Inventory } from "../../domain/model/Inventory";
import { connect } from "../../database/mongodb";


export class MongoInventoryRepository implements InventoryInterface {
    private collection!: Collection | any;

    constructor() {
        this.initializeCollection();
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

    async delete(uuid: string): Promise<void> {
        try {
            this.collection.deleteOne({ uuid: uuid });
            return Promise.resolve();
        } catch (error) {
            return Promise.resolve();
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