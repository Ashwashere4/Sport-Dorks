import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Item } from '../item';
import { InventoryService } from '../inventory.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-item-detail',
  templateUrl: './item-detail.component.html',
  styleUrls: [ './item-detail.component.css' ]
})
export class ItemDetailComponent implements OnInit {
  item: Item | undefined;

  constructor(
    private route: ActivatedRoute,
    private inventoryService: InventoryService,
    private location: Location,
    private cartService: CartService
  ) {}

  addToCart(item: Item) {
    this.cartService.addToCart(item);
    window.alert('Your product has been added to the cart!');
  }

  ngOnInit(): void {
    this.getItem();
  }

  getItem(): void {
    const name = this.route.snapshot.paramMap.get('name')!;
    this.inventoryService.getItem(name)
      .subscribe(item => this.item = item);
  }

  goBack(): void {
    this.location.back();
  }

  // save(): void {
  //   if (this.item) {
  //     this.inventoryService.updateItem(this.item)
  //       .subscribe(() => this.goBack());
  //   }
  // }
}