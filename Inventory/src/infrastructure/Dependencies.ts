import { CreateInventoryController } from './controllers/CreateInventoryController';
import { DeleteInventoryUseCases } from '../application/useCases/DeleteInventoryUseCases';
import { ListInventoryUseCases } from '../application/useCases/ListInventoryUseCases';
import { CreateInventoryUsecases } from './../application/useCases/CreateInventoryUseCases';
import { MongoInventoryRepository } from "./repositories/MongoInventoryRepository";
import { ListInventoryController } from './controllers/ListInventoryController';
import { DeleteInventoryController } from './controllers/DeleteInventoryController';

export const database = new MongoInventoryRepository();

export const createInventoryUsecases = new CreateInventoryUsecases(database);
export const listInventoryUsecases = new ListInventoryUseCases(database);
export const deleteInventoryUsecases = new DeleteInventoryUseCases(database);

export const createInventoryController = new CreateInventoryController(createInventoryUsecases);
export const listInventoryController = new ListInventoryController(listInventoryUsecases);
export const deleteInventoryController = new DeleteInventoryController(deleteInventoryUsecases);