import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from './item';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  itemsUrl = 'http://localhost:8080/items';
  title = 'estore-ui';
  json = require('./items.json')

  
  // constructor(
  //   private http: HttpClient) { }

  // getItems(): Observable<Item[]> {
  //   return this.http.get<Item[]>(this.itemsUrl);
  // }

 


}