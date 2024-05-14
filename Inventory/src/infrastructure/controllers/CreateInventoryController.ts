import { Request, Response } from 'express';

import { BaseResponse } from '../../application/dtos/response/BaseResponse';
import { CreateInventoryUsecases } from '../../application/useCases/CreateInventoryUseCases';
import { CreateInventoryRequest } from '../../application/dtos/request/CreateInventoryRequest';

export class CreateInventoryController{
    constructor(readonly useCase: CreateInventoryUsecases){}

    async execute(req: Request, res: Response){
        const data = req.body;
        console.log(data);
        const request = new CreateInventoryRequest(data.name, parseInt(data.price), parseInt(data.stock));
        try{
            const baseResponse = await this.useCase.execute(request);
            res.status(baseResponse.statusCode).json(baseResponse);
        }catch(error){
            console.log(error);
            let baseResponse = new BaseResponse("Error", "Internal server error", false, 500);
            res.status(baseResponse.statusCode).json(baseResponse);
        }
    }
}