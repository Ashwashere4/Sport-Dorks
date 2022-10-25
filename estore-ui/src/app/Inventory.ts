import { Item } from "./item";

export interface Inventory {
    inventory: Map<string, Item>;
}