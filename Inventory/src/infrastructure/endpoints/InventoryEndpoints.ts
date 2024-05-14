import { Express } from "express";
import { createInventoryController, deleteInventoryController, listInventoryController } from "../Dependencies";

const MODEL_URL = "inventory/";
const BASE_URL = process.env.BASE_URL || "/api/v1/";

export function setupInventoryEndpoints(app: Express) {
    app.get(`${BASE_URL}${MODEL_URL}health`, (req, res) => {
        res.status(200).json({ status: 'OK' });
    });
    app.post(`${BASE_URL}${MODEL_URL}`, createInventoryController.execute.bind(createInventoryController));
    app.delete(`${BASE_URL}${MODEL_URL}:uuid`, deleteInventoryController.execute.bind(deleteInventoryController));
    app.get(`${BASE_URL}${MODEL_URL}`, listInventoryController.execute.bind(listInventoryController));
}