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
  item: Item[] = [];
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

  }

  goBack(): void {
    this.location.back();
  }

  parseInt(string: string): number {
    return parseInt(string);
  }

  save(quantity: number): void {
    if (this.item) {
      this.inventoryService.updateItem(this.item[0], this.item[0].name, quantity, this.item[0].cost)
        .subscribe(() => this.goBack());
    }
  }
}