import { Inventory } from './../model/Inventory';


export interface InventoryInterface {
    create(inventory: Inventory): Promise<Inventory|null>;
    delete(uuid: string): Promise<void>;
    list(): Promise<Inventory[]|null>;
    get(uuid: string): Promise<Inventory|null>;
    decreaseStock(uuid:string,stock:number):Promise<Inventory|null>;
}