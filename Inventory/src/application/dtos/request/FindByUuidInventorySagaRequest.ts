export class FindByUuidInventorySagaRequest{
    public uuid: string;
    public inventories: string[];

    constructor(uuid: string, inventories: string[]){
        this.uuid = uuid;
        this.inventories = inventories;
    }
}