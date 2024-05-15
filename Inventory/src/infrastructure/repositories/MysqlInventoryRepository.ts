import { query } from "../../database/mysqldb";
import { Inventory } from "../../domain/model/Inventory";
import { InventoryInterface } from "../../domain/port/InventoryInterface";

export class MysqlInventoryRepository implements InventoryInterface {
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