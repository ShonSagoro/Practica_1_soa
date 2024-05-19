import express from 'express';
import morgan from 'morgan';
import dotenv from 'dotenv';
import path from 'path';
import cors from 'cors';
import {Signale} from "signale";

import { setupInventoryEndpoints } from './infrastructure/endpoints/InventoryEndpoints';
import { DecreaceSoldProductUseCaseService, FindInventoriesByUuidUseCaseService } from './infrastructure/Dependencies';

dotenv.config();

const app = express();
const signale = new Signale();
app.use(cors());


const HOST:string = process.env.HOST_SERVER || '0.0.0.0';
const PORT:number  = Number(process.env.PORT_SERVER) || 8080;

app.use(express.static(path.join(__dirname, './public/images')));
app.use(express.json()); 
app.use(morgan('dev'));
setupInventoryEndpoints(app);
let server = null;



async function startServer() {
    await DecreaceSoldProductUseCaseService();
    await FindInventoriesByUuidUseCaseService();
    server = app.listen(PORT, HOST, () => {
        signale.success(`Server is running on http://${HOST}:${PORT}`);
    });
    
}

startServer();

export { app, server };