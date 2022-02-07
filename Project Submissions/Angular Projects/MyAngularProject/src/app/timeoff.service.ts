import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, map, of } from 'rxjs';

import { MessageService } from './message.service';
import { TimeOff } from './time-off';

@Injectable({
  providedIn: 'root'
})
export class TimeoffService {
  
  private timeoffUrl = 'api/time-off'; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getTimeOffs(): Observable<TimeOff[]> {
    return this.http.get<TimeOff[]>(this.timeoffUrl)
      .pipe(
        tap(_ => this.log('fetched timeoffs')),
        catchError(this.handleError<TimeOff[]>('getTimeOffs', []))
      );
  }

  /** GET hero by id. Return `undefined` when id not found */
  getTimeOffNo404<Data>(id: number): Observable<TimeOff> {
    const url = `${this.timeoffUrl}/?id=${id}`;
    return this.http.get<TimeOff[]>(url)
      .pipe(
        map(TimeOffs => TimeOffs[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} timeoff id=${id}`);
        }),
        catchError(this.handleError<TimeOff>(`getTimeOff id=${id}`))
      );
  }

  /** GET hero by id. Will 404 if id not found */
  getTimeOff(id: number): Observable<TimeOff> {
    const url = `${this.timeoffUrl}/${id}`;
    return this.http.get<TimeOff>(url).pipe(
      tap(_ => this.log(`fetched timeoff id=${id}`)),
      catchError(this.handleError<TimeOff>(`gettimeoff id=${id}`))
    );
  }

  /* GET heroes whose name contains search term */
  searchTimeOff(term: string): Observable<TimeOff[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<TimeOff[]>(`${this.timeoffUrl}/?sender=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found timeoffs matching "${term}"`) :
         this.log(`no timeoffs matching "${term}"`)),
      catchError(this.handleError<TimeOff[]>('searchTimeOffs', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addTimeOff(timeoff: TimeOff): Observable<TimeOff> {
    return this.http.post<TimeOff>(this.timeoffUrl, timeoff, this.httpOptions).pipe(
      tap((newTimeOff: TimeOff) => this.log(`added timeoff w/ id=${newTimeOff.id}`)),
      catchError(this.handleError<TimeOff>('addAssociate'))
    );
  }

  /** DELETE: delete the hero from the server */
  deleteTimeOff(id: number): Observable<TimeOff> {
    const url = `${this.timeoffUrl}/${id}`;

    return this.http.delete<TimeOff>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted timeoff id=${id}`)),
      catchError(this.handleError<TimeOff>('deleteTimeOff'))
    );
  }

  /** PUT: update the hero on the server */
  updateTimeOff(timeoff: TimeOff): Observable<any> {
    return this.http.put(this.timeoffUrl, timeoff, this.httpOptions).pipe(
      tap(_ => this.log(`updated timeoff id=${timeoff.id}`)),
      catchError(this.handleError<any>('updateTimeOff'))
    );
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

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`TimeOffService: ${message}`);
  }
}