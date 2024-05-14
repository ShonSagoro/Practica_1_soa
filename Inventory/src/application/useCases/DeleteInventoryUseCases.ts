import { InventoryInterface } from "../../domain/port/InventoryInterface";
import { BaseResponse } from "../dtos/response/BaseResponse";

export class DeleteInventoryUseCases{

    constructor(readonly repository: InventoryInterface) {
    }

    async execute(uuid: string): Promise<BaseResponse> {
        await this.repository.delete(uuid);
        return new BaseResponse(null, "Invetory deleted successfully", true, 200);
    }
}