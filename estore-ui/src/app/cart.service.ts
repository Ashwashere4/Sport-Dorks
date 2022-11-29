import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs';
import { Item } from './item'; 
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})

export class CartService {
  
  private cartUrl = 'http://localhost:8080/ShoppingCart';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
  ){}

  getCart():Observable<Item[]> {
    return this.http.get<Item[]>(this.cartUrl).pipe
      (catchError(this.handleError<Item[]>('getShoppingCart', [])));
  }

  getItem(name: string): Observable<Item> {
    const url = `${this.cartUrl}/${name}`;
    return this.http.get<Item>(url).pipe
      (tap(_ => this.log(`fetched item name=${name}`), 
      catchError(this.handleError<Item>(`getItem name=${name}`))));
  }

  private log(message: string) {
    this.messageService.add(`CartService: ${message}`);
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
  updateItem(item: string, name: string, quantity: string, cost: string): Observable<any> {

  return this.http.put<Item>(this.cartUrl, {item, name, quantity, cost}, this.httpOptions).pipe(
    tap((updateItem: Item) => this.log(`updated item w/ name, quantity, cost = ${updateItem.name}, ${updateItem.quantity}, ${updateItem.cost}`)),
    catchError(this.handleError<Item>('updateItem'))
  );

  }

  createItem(name: string, quantity: number, cost: number): Item {
    return {name, quantity, cost};
  }

  /** POST: add a new item to the server */
  addItem(item: Item): Observable<Item> {

    return this.http.post<Item>(this.cartUrl, item, this.httpOptions).pipe(
      tap((newItem: Item) => this.log(`added item w/ quantity=${newItem.quantity} and cost=${newItem.cost}`)),
      catchError(this.handleError<Item>('addItem'))
    );
  }

  /** DELETE: delete the item from the server */
  deleteItem(name: string): Observable<Item> {
    const url = `${this.cartUrl}/${name}`;

    return this.http.delete<Item>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted item name=${name}`)),
      catchError(this.handleError<Item>('deleteItem'))
    );
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
}
