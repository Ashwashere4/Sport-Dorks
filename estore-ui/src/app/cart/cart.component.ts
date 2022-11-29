import { Component, OnInit } from '@angular/core';

import { CartService } from '../cart.service';
import { Item } from '../item';
import { MessageService } from '../message.service';
import { ITEMS } from '../mock-inventory';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit{

  title:any;

  constructor(
    private cartService: CartService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.getCart();
  }

  selectedItem?: Item;
  json = require('../shoppingCart.json')
  
  cart: Item[] = [];
  updatedItem?: Item;

  onSelect(item: Item): void{
    this.selectedItem = item;
    this.messageService.add(`CartComponent: Selected item name=${item.name}`)
  }

  getCart(): void{
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  add(name: string, quantity: number, cost: number): void {
    name = name.trim();
    if (!name) { return; }
    const newItem = this.cartService.createItem(name, quantity, cost);;
    this.cartService.addItem(newItem)
      .subscribe(newItem => {
        this.cart.push(newItem);
      });
  }

  delete(item: Item): void {
    this.cart = this.cart.filter(i => i !== item);
    this.cartService.deleteItem(item.name).subscribe();
  }

  purchaseAll(): void{
    this.cartService.deleteAll();
  }

  update(item: string, name: string, quantity: string, cost: string): void{

    console.log(item)
    this.cartService.updateItem(item, name, quantity, cost).subscribe();

  }

  parseInt(string: string): number {
    return parseInt(string);
  }
}
