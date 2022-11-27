import { Injectable } from '@angular/core';
import { Item } from './item'; 

@Injectable({
  providedIn: 'root'
})

export class CartService {
  items: Item[] = []; 

  addToCart(cart: Item) {
    this.items.push(cart);
  }

  getItems() {
    return this.items;
  }

  clearCart() {
    this.items = [];
    return this.items;
  }

  removelastItem(){
    this.items.pop
  }

}
