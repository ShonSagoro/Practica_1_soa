import { Express } from "express";
import { createInventoryController, deleteInventoryController, listInventoryController } from "../Dependencies";

export function setupInventoryEndpoints(app: Express) {
    app.get(`/health`, (req, res) => {
        res.status(200).json({ status: 'OK' });
    });
    app.post(`/`, createInventoryController.execute.bind(createInventoryController));
    app.delete(`/:uuid`, deleteInventoryController.execute.bind(deleteInventoryController));
    app.get(`/`, listInventoryController.execute.bind(listInventoryController));
}