import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Item } from '../item';
import { MessageService } from '../message.service';
import { WishListService } from '../wish-list.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  title:any;

  constructor(
    private listService: WishListService,
    private cartService: CartService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.getCart();
    this.getList();
  }

  selectedItem?: Item;
  json = require('../wishlist.json')
  
  list: Item[] = [];
  cart: Item[] = [];
  updatedItem?: Item;

  onSelect(item: Item): void{
    this.selectedItem = item;
    this.messageService.add(`CartComponent: Selected item name=${item.name}`)
  }

  getList():void {
    this.listService.getList().subscribe(list => this.list = list);
  }
  getCart(): void{
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  add(name: string, quantity: number, cost: number): void {
    name = name.trim();
    if (!name) { return; }
    const newItem = this.cartService.createItem(name, quantity, cost);;
    this.listService.addToList(newItem)
      .subscribe(newItem => {
        this.cart.push(newItem);
      });
  }

  delete(item: Item): void {
    this.list = this.list.filter(i => i !== item);
    this.listService.deleteItem(item.name).subscribe();
  }

  addtocart(item: Item): void{
    this.list = this.cart.filter(i => i !== item);
    this.cartService.addItem(item).subscribe();
    this.listService.deleteItem(item.name).subscribe();
  }

  parseInt(string: string): number {
    return parseInt(string);
  }
}
