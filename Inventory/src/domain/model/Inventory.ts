import { v4 as uuidv4 } from 'uuid';

export class Inventory{
    public uuid: string;
    public name: string;
    public price: number;
    public stock: number;


    constructor(name: string, price: number, stock: number){
        this.uuid = uuidv4();
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}