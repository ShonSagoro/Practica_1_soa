import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { BaseResponse } from "../dtos/response/BaseResponse";

export class DeleteInventoryUseCases{

    constructor(readonly repository: IInventoryRepository) {
    }

    async execute(uuid: string): Promise<BaseResponse> {
        let result = await this.repository.delete(uuid);
        if (!result) {
            return new BaseResponse(null, "Inventory not deleted", false, 400);
        }
        return new BaseResponse(null, "Invetory deleted successfully", true, 200);
    }
}