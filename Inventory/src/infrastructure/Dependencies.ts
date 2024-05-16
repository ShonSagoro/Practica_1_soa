import { CreateInventoryController } from './controllers/CreateInventoryController';
import { DeleteInventoryUseCases } from '../application/useCases/DeleteInventoryUseCases';
import { ListInventoryUseCases } from '../application/useCases/ListInventoryUseCases';
import { CreateInventoryUsecases } from './../application/useCases/CreateInventoryUseCases';
import { ListInventoryController } from './controllers/ListInventoryController';
import { DeleteInventoryController } from './controllers/DeleteInventoryController';
import { MysqlInventoryRepository } from './repositories/MysqlInventoryRepository';
import { DecreaceSoldProductUseCase } from '../application/useCases/DecreaseSoldStockUseCases';
import { RabbitMQTrackingReceiver } from './services/RabbitMQTrackingReceiver';

const database = new MysqlInventoryRepository();

const createInventoryUsecases = new CreateInventoryUsecases(database);
const listInventoryUsecases = new ListInventoryUseCases(database);
const deleteInventoryUsecases = new DeleteInventoryUseCases(database);

export const createInventoryController = new CreateInventoryController(createInventoryUsecases);
export const listInventoryController = new ListInventoryController(listInventoryUsecases);
export const deleteInventoryController = new DeleteInventoryController(deleteInventoryUsecases);

export async function DecreaceSoldProductUseCaseService() {
    const decreaseSoldStockUseCase = new DecreaceSoldProductUseCase(database);
    const rabbitMQTrackingReceiver = new RabbitMQTrackingReceiver(decreaseSoldStockUseCase);
    await rabbitMQTrackingReceiver.receive();    
}