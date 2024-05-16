import amqp from 'amqplib';
import dotenv from 'dotenv';


const hostname=process.env.RABBITMQ_HOST||'localhost';
const protocol=process.env.RABBITMQ_PROTOCOL;
const user=process.env.RABBITMQ_USER;
const password=process.env.RABBITMQ_PASS;
const port=process.env.RABBITMQ_PORT;


const rabbitSettings:any={
    protocol: protocol,
    hostname: hostname,
    port: port,
    username: user,
    password: password
}

export async function setupRabbitMQ() {
    dotenv.config();

    const connection = await amqp.connect(rabbitSettings);

    const channel = await connection.createChannel();

    const queueName = process.env.RABBITMQ_QUEUE_NAME || 'inventory-queue';
    const exchangeName = process.env.RABBITMQ_EXCHANGE_NAME || 'order-exchange';
    const routingKey = process.env.RABBITMQ_ROUTING_KEY || 'order-created';

    await channel.assertQueue(queueName);
    await channel.bindQueue(queueName, exchangeName, routingKey);

    return { connection, channel, queueName };
}