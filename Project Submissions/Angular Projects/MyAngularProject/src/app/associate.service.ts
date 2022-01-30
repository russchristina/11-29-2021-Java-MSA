import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Associate } from './associate';
import { MessageService } from './message.service';


@Injectable({ providedIn: 'root' })
export class AssociateService {

  private associatesUrl = 'api/associates';  // URL to web api

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

  /** GET heroes from the server */
  getAssociates(): Observable<Associate[]> {
    return this.http.get<Associate[]>(this.associatesUrl)
      .pipe(
        tap(_ => this.log('fetched associates')),
        catchError(this.handleError<Associate[]>('getAssociates', []))
      );
  }

  /** GET hero by id. Return `undefined` when id not found */
  getAssociateNo404<Data>(id: number): Observable<Associate> {
    const url = `${this.associatesUrl}/?id=${id}`;
    return this.http.get<Associate[]>(url)
      .pipe(
        map(associates => associates[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? 'fetched' : 'did not find';
          this.log(`${outcome} associate id=${id}`);
        }),
        catchError(this.handleError<Associate>(`getAssociate id=${id}`))
      );
  }

  /** GET hero by id. Will 404 if id not found */
  getAssociate(id: number): Observable<Associate> {
    const url = `${this.associatesUrl}/${id}`;
    return this.http.get<Associate>(url).pipe(
      tap(_ => this.log(`fetched associate id=${id}`)),
      catchError(this.handleError<Associate>(`getHero id=${id}`))
    );
  }

  /* GET heroes whose name contains search term */
  searchAssociates(term: string): Observable<Associate[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Associate[]>(`${this.associatesUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found associates matching "${term}"`) :
         this.log(`no associates matching "${term}"`)),
      catchError(this.handleError<Associate[]>('searchAssociates', []))
    );
  }

  //////// Save methods //////////

  /** POST: add a new hero to the server */
  addAssociate(associate: Associate): Observable<Associate> {
    return this.http.post<Associate>(this.associatesUrl, associate, this.httpOptions).pipe(
      tap((newAssociate: Associate) => this.log(`added associate w/ id=${newAssociate.id}`)),
      catchError(this.handleError<Associate>('addAssociate'))
    );
  }

  /** DELETE: delete the hero from the server */
  deleteAssociate(id: number): Observable<Associate> {
    const url = `${this.associatesUrl}/${id}`;

    return this.http.delete<Associate>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted associate id=${id}`)),
      catchError(this.handleError<Associate>('deleteAssociate'))
    );
  }

  /** PUT: update the hero on the server */
  updateAssociate(associate: Associate): Observable<any> {
    return this.http.put(this.associatesUrl, associate, this.httpOptions).pipe(
      tap(_ => this.log(`updated associate id=${associate.id}`)),
      catchError(this.handleError<any>('updateAssociate'))
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
    this.messageService.add(`HeroService: ${message}`);
  }
}