import { Item } from "./item";

export class CreateItem {

    private name = '';
    private quantity = 0;
    private cost = 0;

    private newItem: any;
  
    constructor(itemName: string, itemQuantity: number, itemCost: number) {
      this.name = itemName
      this.quantity = itemQuantity
      this.cost = itemCost;
      this.newItem = {name: itemName, quantity: itemQuantity, cost: itemCost};
    }

    getNewItem(): Item {
        return this.newItem;
    }
  }
