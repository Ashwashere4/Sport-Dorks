import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, tap } from 'rxjs';
import { Item } from './item';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class WishListService {
  list: Item[] = []
  private cartUrl = 'http://localhost:8080/WishList';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
  ) { }

  addToList(item: Item){
    this.list.push(item);
    return this.http.post<Item>(this.cartUrl, item, this.httpOptions).pipe(
      tap((newItem: Item) => this.log(`added item w/ quantity=${newItem.quantity} and cost=${newItem.cost}`)),
      catchError(this.handleError<Item>('addItem'))
    );
  }

  getList():Observable<Item[]>{
    return this.http.get<Item[]>(this.cartUrl).pipe
      (catchError(this.handleError<Item[]>('getWishList', [])));
  }

  addToCart(name:string){
    //add to cart not working
    this.deleteItem(name);
  }

  deleteItem(name: string): Observable<Item> {
    const url = `${this.cartUrl}/${name}`;

    return this.http.delete<Item>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted item name=${name}`)),
      catchError(this.handleError<Item>('deleteItem'))
    );
  }
  
  clearCart(){
    this.list = [];
    return this.list;
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private log(message: string) {
    this.messageService.add(`CartService: ${message}`);
  }

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
}