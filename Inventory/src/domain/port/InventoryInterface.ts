import { Inventory } from './../model/Inventory';


export interface InventoryInterface {
    create(inventory: Inventory): Promise<Inventory|null>;
    delete(uuid: string): Promise<void>;
    list(): Promise<Inventory[]|null>;
}