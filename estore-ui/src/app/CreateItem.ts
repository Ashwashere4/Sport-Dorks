import { Item } from "./item";

export class CreateItem implements Item {

    public name = '';
    public quantity = 0;
    public cost = 0;

    newItem: Item;
  
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
