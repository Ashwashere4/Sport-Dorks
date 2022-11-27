import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, of, tap } from "rxjs";
import { MessageService } from "./message.service";
import { User } from "./user";

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private usersUrl = 'http://localhost:8080/Users';

    constructor(
        private messageService: MessageService,
        private http: HttpClient
    ){}
    
    getUsers():Observable<User[]>{
        return this.http.get<User[]>(this.usersUrl).pipe
        (catchError(this.handleError<User[]>('getUsers', [])));
    }

    getUser(name: string): Observable<User>{
        const url = `${this.usersUrl}/${name}`;
        return this.http.get<User>(url).pipe
            (tap(_=> this.log(`fetched name=${name}`), 
            catchError(this.handleError<User>(`getUser name=${name}`))));
        
    }

    private log(message: string) {
        this.messageService.add(`UsersService: ${message}`);
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

     /** PUT: update the user on the server */
    updateUser(user: User): Observable<any> {
        return this.http.put(this.usersUrl, user, this.httpOptions).pipe(
        tap(_ => this.log(`updated user admin=${user.admin} and owner=${user.owner}`)),
        catchError(this.handleError<any>('updateUser'))
        );
    }
    
    createUser(username: string, password: string, admin: boolean, owner: boolean): User {
        return {username, password, admin, owner};
    }
    
    /** POST: add a new user to the server */
    addUser(user: User): Observable<User> {
        return this.http.post<User>(this.usersUrl, user, this.httpOptions).pipe(
        tap((newUser: User) => this.log(`added user w/ password=${newUser.password}`)),
        catchError(this.handleError<User>('addUser'))
        );
    }
    
    /** DELETE: delete the User from the server */
    deleteUser(username: string): Observable<User> {
        const url = `${this.usersUrl}/${username}`;
    
        return this.http.delete<User>(url, this.httpOptions).pipe(
        tap(_ => this.log(`deleted user with username=${username}`)),
        catchError(this.handleError<User>('deleteUser'))
        );
    }
    
    // /* GET players whose name contains search term */
    // searchTeam(term: string): Observable<User[]> {
    //     if (!term.trim()) {
    //     // if not search term, return empty player array.
    //     return of([]);
    //     }
    //     return this.http.get<User[]>(`${this.usersUrl}/?name=${term}`).pipe(
    //     tap(x => x.length ?
    //         this.log(`found users matching "${term}"`) :
    //         this.log(`no users matching "${term}"`)),
    //     catchError(this.handleError<User[]>('searchTeam', []))
    //     );
    // }
    
    httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
}