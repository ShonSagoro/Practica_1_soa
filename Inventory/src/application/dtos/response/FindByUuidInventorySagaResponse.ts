import { Inventory } from "../../../domain/model/Inventory";

export class FindByUuidInventorySagaResponse{
    public uuid: string;
    public products: Inventory[];

    constructor(uuid: string, products: Inventory[]){
        this.uuid = uuid;
        this.products = products;
    }
}