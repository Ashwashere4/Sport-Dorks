import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { CreateItem } from '../CreateItem'
import { InventoryService } from '../inventory.service';
import { MessageService } from '../message.service';
import { Route, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  title: any;

  constructor(
    private inventoryService: InventoryService, 
    private messageService: MessageService,
    private createItem: CreateItem) { }

  selectedItem?: Item;
  json = require('../items.json')

  ngOnInit(): void {
    this.getItems();
  }

  items: Item[] = [];

  onSelect(item: Item): void {
    this.selectedItem = item;
    this.messageService.add(`InventoryComponent: Selected item name=${item.name}`);
  }

  getItems(): void {
    this.inventoryService.getInventory().subscribe(items => this.items = items);
  }

  add(name: string, quantity: number, cost: number): void {
    name = name.trim();
    if (!name) { return; }
    this.createItem.constructor(name, quantity, cost);
    const newItem = this.createItem.getNewItem
    this.inventoryService.addItem({ newItem } as unknown as Item)
      .subscribe(item => {
        this.items.push(item);
      });
  }

  delete(item: Item): void {
    this.items = this.items.filter(i => i !== item);
    this.inventoryService.deleteItem(item.name).subscribe();
  }
}