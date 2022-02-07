import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { MessageService } from './message.service';
import { ShiftTrade } from './shift-trade';

@Injectable({
  providedIn: 'root'
})
export class ShifttradeService {
  private shifttradesUrl = 'api/shifttrades';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getshiftTrades(): Observable<ShiftTrade[]> {
    return this.http.get<ShiftTrade[]>(this.shifttradesUrl)
      .pipe(
        tap(_ => this.log('fetched shifttrades')),
        catchError(this.handleError<ShiftTrade[]>('getshiftTrades', []))
      );
  }

  /** GET hero by id. Return `undefined` when id not found */
  getShiftTradeNo404<Data>(id: number): Observable<ShiftTrade> {
    const url = `${this.shifttradesUrl}/?id=${id}`;
    return this.http.get<ShiftTrade[]>(url)
      .pipe(
        map(shifttrades => shifttrades[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} shifttrade id=${id}`);
        }),
        catchError(this.handleError<ShiftTrade>(`getShiftTrade id=${id}`))
      );
  }

  /** GET hero by id. Will 404 if id not found */
  getShiftTrade(id: number): Observable<ShiftTrade> {
    const url = `${this.shifttradesUrl}/${id}`;
    return this.http.get<ShiftTrade>(url).pipe(
      tap(_ => this.log(`fetched shifttrade id=${id}`)),
      catchError(this.handleError<ShiftTrade>(`getShiftTrade id=${id}`))
    );
  }

  /* GET heroes whose name contains search term */
  searchShiftTrades(term: string): Observable<ShiftTrade[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<ShiftTrade[]>(`${this.shifttradesUrl}/?sender=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found shifttrades matching "${term}"`) :
         this.log(`no shifttrades matching "${term}"`)),
      catchError(this.handleError<ShiftTrade[]>('searchShiftTrades', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addShiftTrade(shiftTrade: ShiftTrade): Observable<ShiftTrade> {
    return this.http.post<ShiftTrade>(this.shifttradesUrl, shiftTrade, this.httpOptions).pipe(
      tap((newShiftTrade: ShiftTrade) => this.log(`added associate w/ id=${newShiftTrade.id}`)),
      catchError(this.handleError<ShiftTrade>('addShiftTrade'))
    );
  }

  /** DELETE: delete the hero from the server */
  deleteShiftTrade(id: number): Observable<ShiftTrade> {
    const url = `${this.shifttradesUrl}/${id}`;

    return this.http.delete<ShiftTrade>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted shifttrade id=${id}`)),
      catchError(this.handleError<ShiftTrade>('deleteShiftTrade'))
    );
  }

  /** PUT: update the hero on the server */
  updateShiftTrade(shifttrade: ShiftTrade): Observable<any> {
    return this.http.put(this.shifttradesUrl, shifttrade, this.httpOptions).pipe(
      tap(_ => this.log(`updated shifttrade id=${shifttrade.id}`)),
      catchError(this.handleError<any>('updateShiftTrade'))
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
    this.messageService.add(`ShiftTradeService: ${message}`);
  }
}