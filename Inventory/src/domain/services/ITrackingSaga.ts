export interface ITrackingSaga{
    receive(): Promise<void>;
}