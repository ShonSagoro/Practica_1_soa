import { CreateInventoryRequest } from './../dtos/request/CreateInventoryRequest';
import { BaseResponse } from "../dtos/response/BaseResponse";
import { InventoryInterface } from '../../domain/port/InventoryInterface';
import { Inventory } from '../../domain/model/Inventory';
import { InventoryResponse } from '../dtos/response/InventoryResponse';

export class CreateInventoryUsecases {
    constructor(readonly repository: InventoryInterface) { }

    async execute(request: CreateInventoryRequest): Promise<BaseResponse> {
        let inventory = new Inventory(request.name, request.price, request.stock);
        console.log(inventory);
        let result = await this.repository.create(inventory);
        console.log(result);
        if (result) {
            let response = new InventoryResponse(result.uuid, result.name, result.price, result.stock);
            return new BaseResponse(response, "Inventory created", true, 201);
        } else {
            return new BaseResponse(null, "Inventory not created", false, 400);
        }
    }
}