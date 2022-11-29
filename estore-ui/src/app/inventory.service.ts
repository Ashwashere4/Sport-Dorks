import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs';

import { Item } from './item';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private inventoryUrl = 'http://localhost:8080/Inventory';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
    ) { }

  getInventory():Observable<Item[]> {
    return this.http.get<Item[]>(this.inventoryUrl).pipe
      (catchError(this.handleError<Item[]>('getInventory', [])));
  }

  getItem(name: string): Observable<Item> {
    const url = `${this.inventoryUrl}/${name}`;
    return this.http.get<Item>(url).pipe
      (tap(_ => this.log(`fetched item name=${name}`), 
      catchError(this.handleError<Item>(`getItem name=${name}`))));
  }

  private log(message: string) {
    this.messageService.add(`InventoryService: ${message}`);
  }

  /**
 * Handle Http operation that failed.
 * Let the app continue.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
    };
  }

  /** PUT: update the Item on the server */
updateItem(item: Item, name: string, quantity: number, cost: number): Observable<any> {

    

  return this.http.put<Item>(this.inventoryUrl, {item, name, quantity, cost}, this.httpOptions).pipe(
    tap((updateItem: Item) => this.log(`updated item w/ name, quantity, cost = ${updateItem.name}, ${updateItem.quantity}, ${updateItem.cost}`)),
    catchError(this.handleError<Item>('updateItem'))
  );
  

}

createItem(name: string, quantity: number, cost: number): Item {
  return {name, quantity, cost};
}

/** POST: add a new item to the server */
addItem(item: Item): Observable<Item> {
  return this.http.post<Item>(this.inventoryUrl, item, this.httpOptions).pipe(
    tap((newItem: Item) => this.log(`added item w/ quantity=${newItem.quantity} and cost=${newItem.cost}`)),
    catchError(this.handleError<Item>('addItem'))
  );
}

/** DELETE: delete the hero from the server */
deleteItem(name: string): Observable<Item> {
  const url = `${this.inventoryUrl}/${name}`;

  return this.http.delete<Item>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted item name=${name}`)),
    catchError(this.handleError<Item>('deleteItem'))
  );
}

/* GET heroes whose name contains search term */
searchInventory(term: string): Observable<Item[]> {
  if (!term.trim()) {
    // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<Item[]>(`${this.inventoryUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found items matching "${term}"`) :
       []),
    catchError(this.handleError<Item[]>('searchInventory', []))
  );
}

httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

}
