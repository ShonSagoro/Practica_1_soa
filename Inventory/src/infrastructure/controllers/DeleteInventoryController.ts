import { Request, Response } from 'express';

import { BaseResponse } from '../../application/dtos/response/BaseResponse';
import { DeleteInventoryUseCases } from '../../application/useCases/DeleteInventoryUseCases';

export class DeleteInventoryController{
    constructor(readonly useCase: DeleteInventoryUseCases){}

    async execute(req: Request, res: Response){
        const uuid = req.params.uuid;
        try{
            const baseResponse = await this.useCase.execute(uuid);
            res.status(baseResponse.statusCode).json(baseResponse);
        }catch(error){
            let baseResponse = new BaseResponse("Error", "Internal server error", false, 500);
            res.status(baseResponse.statusCode).json(baseResponse);
        }
    }
}