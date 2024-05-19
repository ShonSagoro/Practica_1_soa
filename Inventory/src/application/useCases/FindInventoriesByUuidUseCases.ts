import { Inventory } from "../../domain/model/Inventory";
import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { FindByUuidInventorySagaRequest } from "../dtos/request/FindByUuidInventorySagaRequest";
import { FindByUuidInventorySagaResponse } from "../dtos/response/FindByUuidInventorySagaResponse";

export class FindInventoriesByUuidUseCases {

    constructor(readonly repository: IInventoryRepository) {
    }

    async execute(request: FindByUuidInventorySagaRequest): Promise<FindByUuidInventorySagaResponse> {
        let inventories: Inventory[] = [];

        for (const inventory of request.inventories) {
            let result = await this.repository.findByUuid(inventory);
            if (result) {
                inventories.push(result);
            }
        }
        if (inventories.length > 0) {
            return new FindByUuidInventorySagaResponse(request.uuid, inventories);
        }else{
            return new FindByUuidInventorySagaResponse(request.uuid, []);
        }
    }
}