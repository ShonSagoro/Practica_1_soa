import { Inventory } from './../../domain/model/Inventory';
import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { IInventorySaga } from "../../domain/services/IInventorySaga";
import { Signale } from "signale";
import { setupRabbitMQ } from "../config/RabbitConfig";
import { FindByUuidInventorySagaRequest } from "../../application/dtos/request/FindByUuidInventorySagaRequest";
import { FindInventoriesByUuidUseCases } from '../../application/useCases/FindInventoriesByUuidUseCases';
import { sign } from 'crypto';

export class InventorySagaImpl implements IInventorySaga{
    private exchangeName: string =process.env.RABBIT_EXCHANGE_INVENTORY||"";
    private queueResponse: string =process.env.RABBIT_QUEUE_INVENTORY_RESPONSE||"";
    private queueRequest: string =process.env.RABBIT_QUEUE_INVENTORY_REQUEST||"";
    private routingKeyResponse: string =process.env.RABBIT_ROUTING_KEY_INVENTORY_RESPONSE||"";
    private routingKeyRequest: string =process.env.RABBIT_ROUTING_KEY_INVENTORY_REQUEST||"";
    private findInventoriesByUuidUseCases: FindInventoriesByUuidUseCases;

    constructor(findInventoriesByUuidUseCases: FindInventoriesByUuidUseCases){
        this.findInventoriesByUuidUseCases = findInventoriesByUuidUseCases;
    }

    async sendInventory(): Promise<void> {
        const signale = new Signale();
        try {
            const  channel  = await setupRabbitMQ(this.queueRequest, this.exchangeName, this.routingKeyRequest);

            signale.info(`Waiting for messages in ${this.queueRequest}.`);
            
            channel.consume(this.queueRequest, async (msg) => {
                if (msg) {
                    signale.info('Message received:', msg.content.toString());
                    const request: FindByUuidInventorySagaRequest = JSON.parse(msg.content.toString()) as FindByUuidInventorySagaRequest;
                    signale.info('Message received:', request);
                    const response = await this.findInventoriesByUuidUseCases.execute(request);
                    const message = Buffer.from(JSON.stringify(response));
                    channel.ack(msg);
                    
                    const channelResponse = await setupRabbitMQ(this.queueResponse, this.exchangeName, this.routingKeyResponse);
                    channelResponse.publish(this.exchangeName, this.routingKeyResponse, message);
                    signale.info('Message sent:', message.toString(), 'to', this.routingKeyResponse);
                }
            });
        } catch (error) {
            signale.error('Error receiving message:', error);
        }
    }
}