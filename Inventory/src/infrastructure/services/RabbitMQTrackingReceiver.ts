import amqp from 'amqplib';
import { RabbitMQReceiver } from "../../domain/services/RabbitMQReceiver";
import {Signale} from "signale";
import { setupRabbitMQ } from '../config/RabbitConfig';
import { DecreaceSoldProductUseCase } from '../../application/useCases/DecreaseSoldStockUseCases';
import { Order } from '../../domain/model/Order';


export class RabbitMQTrackingReceiver implements RabbitMQReceiver {


    constructor(readonly useCase:DecreaceSoldProductUseCase) {

    }

    async receive(): Promise<void> {
        const signale = new Signale();
        try {
            const { connection, channel, queueName } = await setupRabbitMQ();
            await channel.assertQueue(queueName, { durable: true });

            signale.info(`Waiting for messages in ${queueName}`);
            
            channel.consume(queueName, (msg) => {
                if (msg) {
                    signale.info('Message received:', msg.content.toString());
                    const content:any = JSON.parse(msg.content.toString()) as Order;
                    signale.info('Message received:', content);
                    const order = content;
        
                    for (const product of order.products) {
                        this.useCase.execute(product.productUuid, product.quantity);
                    }                    
                    channel.ack(msg);
                }
            });
        } catch (error) {
            signale.error('Error receiving message:', error);
        }
    }

    private async setupRabbitMQ(){
        return await setupRabbitMQ();
    }

}