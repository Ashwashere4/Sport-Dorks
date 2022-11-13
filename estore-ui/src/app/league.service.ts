import { Injectable } from '@angular/core';
import { Observable, of} from 'rxjs';
import { catchError, map, tap } from 'rxjs';

import { Team } from './team';
import { TEAMS } from './mock-league';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from './player';

@Injectable({
  providedIn: 'root'
})
export class LeagueService {

  private leagueUrl = 'http://localhost:8080/League';

  constructor(
    private messageService: MessageService,
    private http: HttpClient
    ) { }

  getLeague():Observable<Team[]> {
    return this.http.get<Team[]>(this.leagueUrl).pipe
      (catchError(this.handleError<Team[]>('getLeague', [])));
  }

  getTeam(id: number): Observable<Team> {
    const url = `${this.leagueUrl}/${id}`;
    return this.http.get<Team>(url).pipe
      (tap(_ => this.log(`fetched team name=${id}`), 
      catchError(this.handleError<Team>(`getTeam name=${id}`))));
  }

  private log(message: string) {
    this.messageService.add(`LeagueService: ${message}`);
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

  /** PUT: update the team on the server */
updateTeam(team: Team): Observable<any> {
  return this.http.put(this.leagueUrl, team, this.httpOptions).pipe(
    tap(_ => this.log(`updated league roster=${team.team} and id=${team.id}`)),
    catchError(this.handleError<any>('updateTeam'))
  );
}

createTeam(team: Player[], id: number): Team {
  return {team, id};
}

/** POST: add a new team to the server */
addTeam(team: Team): Observable<Team> {
  return this.http.post<Team>(this.leagueUrl, team, this.httpOptions).pipe(
    tap((newTeam: Team) => this.log(`added team w/ roster=${newTeam.team} and id=${newTeam.id}`)),
    catchError(this.handleError<Team>('addTeam'))
  );
}

/** DELETE: delete the team from the server */
deleteTeam(id: number): Observable<Team> {
  const url = `${this.leagueUrl}/${name}`;

  return this.http.delete<Team>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted team name=${name}`)),
    catchError(this.handleError<Team>('deleteTeam'))
  );
}

/* GET teams whose name contains search term */
searchLeague(term: string): Observable<Team[]> {
  if (!term.trim()) {
    // if not search term, return empty team array.
    return of([]);
  }
  return this.http.get<Team[]>(`${this.leagueUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found teams matching "${term}"`) :
       this.log(`no teams matching "${term}"`)),
    catchError(this.handleError<Team[]>('searchLeague', []))
  );
}

httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

}
