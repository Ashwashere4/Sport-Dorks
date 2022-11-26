import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs';

import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from './player';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private teamUrl = 'http://localhost:8080/Team';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
    ) { }

  getTeam():Observable<Player[]> {
    return this.http.get<Player[]>(this.teamUrl).pipe
      (catchError(this.handleError<Player[]>('getTeam', [])));
  }

  getPlayer(name: string): Observable<Player> {
    const url = `${this.teamUrl}/${name}`;
    return this.http.get<Player>(url).pipe
      (tap(_ => this.log(`fetched player name=${name}`), 
      catchError(this.handleError<Player>(`getPlayer name=${name}`))));
  }

  private log(message: string) {
    this.messageService.add(`TeamService: ${message}`);
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

      console.error(error); // log to console instead

      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
      };
    }

  /** PUT: update the player on the server */
  updatePlayer(player: Player): Observable<any> {
  return this.http.put(this.teamUrl, player, this.httpOptions).pipe(
    tap(_ => this.log(`updated player age=${player.age} and rating=${player.rating}`)),
    catchError(this.handleError<any>('updatePlayer'))
  );
}

createPlayer(name: string, age: number, rating: number): Player {
  return {name, age, rating};
}

/** POST: add a new player to the server */
addPlayer(player: Player): Observable<Player> {
  return this.http.post<Player>(this.teamUrl, player, this.httpOptions).pipe(
    tap((newPlayer: Player) => this.log(`added player w/ age=${newPlayer.age} and rating=${newPlayer.rating}`)),
    catchError(this.handleError<Player>('addPlayer'))
  );
}

/** DELETE: delete the player from the server */
deletePlayer(name: string): Observable<Player> {
  const url = `${this.teamUrl}/${name}`;

  return this.http.delete<Player>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted player name=${name}`)),
    catchError(this.handleError<Player>('deletePlayer'))
  );
}

/* GET players whose name contains search term */
searchTeam(term: string): Observable<Player[]> {
  if (!term.trim()) {
    // if not search term, return empty player array.
    return of([]);
  }
  return this.http.get<Player[]>(`${this.teamUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found players matching "${term}"`) :
       this.log(`no players matching "${term}"`)),
    catchError(this.handleError<Player[]>('searchTeam', []))
  );
}

httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

}
