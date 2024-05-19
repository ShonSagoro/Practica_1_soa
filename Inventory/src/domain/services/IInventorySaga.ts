export interface IInventorySaga{
    sendInventory(): Promise<void>;
}