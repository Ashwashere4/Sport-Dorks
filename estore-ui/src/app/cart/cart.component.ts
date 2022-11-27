import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Item } from '../item';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent {

  items = this.cartService.getItems();

  constructor(
    private cartService: CartService
  ) { }

  deleteItem(){
    this.cartService.removelastItem;
  }

  addItem(item: Item){
    this.cartService.addToCart(item);
  }
}
