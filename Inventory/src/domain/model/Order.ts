import { v4 as uuidv4 } from 'uuid';
import { OrderProduct } from './OrderProduct';

export class Order{
    public uuid: string;
    public total:number;
    public date: string;
    public status: string;
    public products: OrderProduct[]  = [];


    constructor(total:number,date:string,status:string,products:OrderProduct[]){
        this.uuid = uuidv4();
        this.total = total; 
        this.date = date;
        this.status = status;
        this.products = products;    
    }
}