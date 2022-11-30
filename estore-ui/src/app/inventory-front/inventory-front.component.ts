import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';

import { Item } from '../item';
import { InventoryService } from '../inventory.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-inventory-front',
  templateUrl: './inventory-front.component.html',
  styleUrls: ['./inventory-front.component.css']
})
export class InventoryFrontComponent implements OnInit {

  item: Item | undefined ;
  selectedItem?: Item;
  json = require('../items.json')
  items: Item[] = [];
  cart: Item[] = [];

  constructor(
    private router:Router,
    private inventoryService: InventoryService, 
    private cartService: CartService
    ) { }



  ngOnInit(): void {
    this.getItems();
  }

  addToCart(item: Item) {
    this.getCart();
    const newItem = this.inventoryService.createItem(item.name, 1, item.cost);;
    this.cartService.addItem(newItem)
      .subscribe(newItem => {
        this.cart.push(newItem);
      });

    window.alert('Your product has been added to the cart!');
  }

  parseInt(string: string): number {
    return parseInt(string);
  }

  getItem(detailItem: Item){
    localStorage.setItem("itemName", detailItem.name);
    localStorage.setItem("Q", String(detailItem.quantity))
    localStorage.setItem("itemCost", String(detailItem.cost));
  }

  getItems(): void {
    this.inventoryService.getInventory().subscribe(items => this.items = items);
  }

  getCart(): void {
    this.cartService.getCart().subscribe(cart => this.cart = cart);
  }

  searchItems(): void {

  let name = prompt("Search Inventory");
  
  
  if (name != null){
    
  let x = this.inventoryService.searchInventory(name).subscribe(items => this.items = items)

  }

  else {
    this.inventoryService.getInventory().subscribe(items => this.items = items)
  }

  }

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

}
