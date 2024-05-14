export class InventoryResponse{
    public uuid: string;
    public name: string;
    public price: number;
    public stock: number;


    constructor(uuid:string, name: string, price: number, stock: number){
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}