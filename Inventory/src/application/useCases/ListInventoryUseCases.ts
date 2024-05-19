import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { BaseResponse } from "../dtos/response/BaseResponse";
import { InventoryResponse } from "../dtos/response/InventoryResponse";

export class ListInventoryUseCases {
    constructor(readonly repository: IInventoryRepository) { }

    async execute(): Promise<BaseResponse> {
        let result = await this.repository.list();
        if (result) {
            let response = result.map(invetory => new InventoryResponse(invetory.uuid, invetory.name, invetory.price, invetory.stock));
            return new BaseResponse(response, "Inventories listed", true, 200);
        } else {
            return new BaseResponse(null, "Inventories not listed", false, 400);
        }
    }
}