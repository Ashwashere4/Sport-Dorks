import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs';

import { Facilities } from '../facilities';
import { FACILITIES } from '../mock_facilities';
import { MessageService } from '../message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FacilityService {

  private FacilityURL = 'http://localhost:8080/Facilities';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
    ) { }

  getFacilities():Observable<Facilities[]> {
    return this.http.get<Facilities[]>(this.FacilityURL).pipe
      (catchError(this.handleError<Facilities[]>('getFacilities', [])));
  }

  getFacility(code: BigInteger): Observable<Facilities> {
    const url = `${this.FacilityURL}/${code}`;
    return this.http.get<Facilities>(url).pipe
      (tap(_ => this.log(`fetched facilities code=${code}`), 
      catchError(this.handleError<Facilities>(`getFacility code=${code}`))));
  }

  private log(message: string) {
    this.messageService.add(`facilitiesService: ${message}`);
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

//   /** PUT: update the hero on the server */
// updateItem(item: Item): Observable<any> {
//   return this.http.put(this.inventoryUrl, item, this.httpOptions).pipe(
//     tap(_ => this.log(`updated item quantity=${item.quantity} and cost=${item.cost}`)),
//     catchError(this.handleError<any>('updateItem'))
//   );
// }

// createItem(name: string, quantity: number, cost: number): Item {
//   return {name, quantity, cost};
// }

// /** POST: add a new hero to the server */
// addItem(item: Item): Observable<Item> {
//   return this.http.post<Item>(this.inventoryUrl, item, this.httpOptions).pipe(
//     tap((newItem: Item) => this.log(`added hero w/ quantity=${newItem.quantity} and cost=${newItem.cost}`)),
//     catchError(this.handleError<Item>('addItem'))
//   );
// }

/** DELETE: delete the hero from the server */
deleteFacility(code: number): Observable<Facilities> {
  const url = `${this.FacilityURL}/delete/${code}`;

  return this.http.delete<Facilities>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted facility code=${code}`)),
    catchError(this.handleError<Facilities>('deleteFacility'))
  );
}

/* GET heroes whose name contains search term */
searchFacilities(term: string): Observable<Facilities[]> {
  if (!term.trim()) {
    // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<Facilities[]>(`${this.FacilityURL}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found items matching "${term}"`) :
       this.log(`no items matching "${term}"`)),
    catchError(this.handleError<Facilities[]>('searchFacilities', []))
  );
}

httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

}