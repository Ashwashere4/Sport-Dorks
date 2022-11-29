import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Item } from '../item';
import { Inventory } from '../Inventory';
import { InventoryService } from '../inventory.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: [ './item-detail.component.css' ]
})
export class ItemDetailComponent implements OnInit {
  item: Item | undefined;
  itemName: string | undefined;
  itemCost: number | undefined;
  name: string | any;
  items: Item[] = [];

  constructor(
    private route: ActivatedRoute,
    private inventoryService: InventoryService,
    private location: Location,
    private cartService: CartService
  ) {}

  addToCart(item: Item, quantity: number) {
    this.cartService.addItem(item);
    window.alert('Your product has been added to the cart!');
  }

  ngOnInit(): void {
    this.getItem();
  }

  getItem(): void {
    this.itemName = localStorage.getItem("itemName")?? '';
    this.itemCost = parseInt(localStorage.getItem("itemCost")?? '');
    this.item = this.inventoryService.createItem(this.itemName, 1, this.itemCost);
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