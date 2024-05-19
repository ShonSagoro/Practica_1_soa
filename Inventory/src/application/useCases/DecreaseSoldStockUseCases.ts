import { IInventoryRepository } from "../../domain/port/IInventoryRepository";
import { BaseResponse } from "../dtos/response/BaseResponse";

export class DecreaceSoldProductUseCase{
    constructor(private repository: IInventoryRepository){}
    async execute(uuid:string,stock:number): Promise<BaseResponse>{
        let result = await this.repository.decreaseStock(uuid,stock);
        if(result){
            return new BaseResponse(null, "Stock decreased", true, 200);
        }else{
            return new BaseResponse(null, "Stock not decreased", false, 400);
        }
    }
}