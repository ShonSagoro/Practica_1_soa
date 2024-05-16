export interface RabbitMQReceiver{
    receive(): Promise<void>;
}