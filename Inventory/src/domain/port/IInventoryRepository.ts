import { Inventory } from '../model/Inventory';


export interface IInventoryRepository {
    create(inventory: Inventory): Promise<Inventory|null>;
    delete(uuid: string): Promise<boolean>;
    list(): Promise<Inventory[]|null>;
    findByUuid(uuid: string): Promise<Inventory|null>;
    decreaseStock(uuid:string,stock:number):Promise<Inventory|null>;
}