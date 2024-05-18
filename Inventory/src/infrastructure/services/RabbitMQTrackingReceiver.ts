import { RabbitMQReceiver } from "../../domain/services/RabbitMQReceiver";
import {Signale} from "signale";
import { setupRabbitMQ } from '../config/RabbitConfig';
import { DecreaceSoldProductUseCase } from '../../application/useCases/DecreaseSoldStockUseCases';
import { Order } from '../../domain/model/Order';


export class RabbitMQTrackingReceiver implements RabbitMQReceiver {
    private queueName: string = process.env.RABBIT_QUEUE_TRACKING || 'default';
    private exchangeName: string = process.env.RABBIT_EXCHANGE_TRACKING || 'default';
    private routingKey: string = process.env.RABBIT_ROUTING_KEY_TRACKING || 'default';

    constructor(readonly useCase:DecreaceSoldProductUseCase) {

    }

    async receive(): Promise<void> {
        const signale = new Signale();
        try {
            const { channel } = await setupRabbitMQ(this.queueName, this.exchangeName, this.routingKey);

            signale.info(`Waiting for messages in inventory-queue.`);
            
            channel.consume(this.queueName, (msg) => {
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

}