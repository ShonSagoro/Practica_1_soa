import { query } from "../../database/mysqldb";
import { Inventory } from "../../domain/model/Inventory";
import { InventoryInterface } from "../../domain/port/InventoryInterface";

export class MysqlInventoryRepository implements InventoryInterface {

    async decreaseStock(uuid: string, stock: number): Promise<Inventory | null> {
        try{
            let product = await this.findByUuid(uuid);
            if(product){
                product.stock -= stock;
                if (product.stock < 0) {
                    product.stock = 0;
                }
                let sql = `UPDATE inventories SET stock = ? WHERE uuid = ?`;
                let params = [product.stock, product.uuid];
                await query(sql, params);
                return product;
            }else{
                return Promise.resolve(null);
            }
        }catch(error){
            return Promise.resolve(null);
        }
    }

   async findByUuid(uuid: string): Promise<Inventory | null> {
        let sql = `SELECT * FROM inventories WHERE uuid = ?`;
        let params = [uuid];
        try{
            const [result]: any = await query(sql, params);
            if(result.length > 0){
                let inventory = new Inventory(result[0].name, result[0].price, result[0].stock);
                inventory.uuid = result[0].uuid;
                return inventory;
            }
            return Promise.resolve(null);
        }catch (error) {
            console.log(error);
            throw new Error('find by uuid error');
        }
    }

    async delete(uuid: string): Promise<void> {
        let sql = `DELETE FROM inventories WHERE uuid = ?`;
        let params = [uuid];
        try {
            await query(sql, params);
            return Promise.resolve();
        } catch (error) {
            throw new Error('Error deleting user');
        }
    }

    async list(): Promise<Inventory[] | null> {
        let sql = `SELECT * FROM inventories`;
        try {
            const [result]: any = await query(sql, []);
            if (result) {
                return result.map((element: any) => {
                    let inventory = new Inventory(element.name, element.price, element.stock);
                    inventory.uuid = element.uuid;
                    return inventory;
                });
            }
            return Promise.resolve(null);
        } catch (error) {
            return null;
        }
    }

    async create(inventory: Inventory): Promise<Inventory | null> {
        let sql = `INSERT INTO inventories (uuid, name, price, stock) VALUES (?, ?, ?, ?)`;
        try {
            let params: any[] = [inventory.uuid, inventory.name, inventory.price, inventory.stock];
            await query(sql, params);
            return Promise.resolve(inventory);
        } catch (error) {
            console.log(error);
            return Promise.resolve(null);
        }
    }

} 