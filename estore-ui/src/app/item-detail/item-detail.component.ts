import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

import { Item } from '../item';
import { InventoryService } from '../inventory.service';
import { CartService } from '../cart.service';
import { WishListService } from '../wish-list.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: [ './item-detail.component.css' ]
})
export class ItemDetailComponent implements OnInit {
  item: Item | undefined;
  itemName: string | undefined;
  itemCost: number | undefined;
  itemQ: number | undefined;
  name: string | any;
  items: Item[] = [];
  cart: Item[] = [];

  json = require('../shoppingCart.json')

  constructor(
    private cartService: CartService,
    private listService: WishListService,
    private inventoryService: InventoryService,
    private location: Location
  ) {}

  addToCart(item: Item, quantity: number) {
    const newItem = this.inventoryService.createItem(item.name, quantity, item.cost);;
    this.cartService.addItem(newItem)
      .subscribe(newItem => {
        this.items.push(newItem);
      });

    window.alert('Your product has been added to the cart!');
  }

  addtoList(item: Item){
    this.listService.addToList(item).subscribe();
  }

  getCart(): void {
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  ngOnInit(): void {
    this.getItem();
  }

  getItems(): void {
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  getItem(): void {
    this.itemName = localStorage.getItem("itemName")?? '';
    this.itemQ = parseInt(localStorage.getItem("Q")?? '');
    this.itemCost = parseInt(localStorage.getItem("itemCost")?? '');
    this.item = this.inventoryService.createItem(this.itemName, this.itemQ, this.itemCost);
  }

  goBack(): void {
    this.location.back();
  }

  parseInt(string: string): number {
    return parseInt(string);
  }

  save(quantity: number): void {
    if (this.item) {
      this.inventoryService.updateItem(this.item, this.item.name, quantity, this.item.cost)
        .subscribe(() => this.goBack());
    }
  }
}