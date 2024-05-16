import { v4 as uuidv4 } from 'uuid';

export class OrderProduct{
    public uuid: string;
    public price: number;
    public quantity: number;
    public productUuid: string;
    public orderUuid: string;


    constructor(price: number, quantity: number, productUuid: string, orderUuid: string){
        this.uuid = uuidv4();
        this.price = price;
        this.quantity = quantity;
        this.productUuid = productUuid;
        this.orderUuid = orderUuid;
    }
}