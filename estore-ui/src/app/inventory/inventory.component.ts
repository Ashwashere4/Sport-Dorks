import { Component, OnInit } from '@angular/core';

import { Item } from '../item';
import { Inventory } from '../Inventory';
import { ITEMS } from '../mock-inventory';
import { InventoryService } from '../inventory.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  constructor(
    private inventoryService: InventoryService, 
    private messageService: MessageService
    ) { }

  selectedItem?: Item;

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

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.inventoryService.addItem({ name } as Item)
      .subscribe(item => {
        this.items.push(item);
      });
  }

  delete(item: Item): void {
    this.items = this.items.filter(i => i !== item);
    this.inventoryService.deleteItem(item.name).subscribe();
  }
}
