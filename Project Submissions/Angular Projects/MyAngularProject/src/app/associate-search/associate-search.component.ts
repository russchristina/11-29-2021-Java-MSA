import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Associate } from '../associate';
import { AssociateService } from '../associate.service';

@Component({
  selector: 'app-associate-search',
  templateUrl: './associate-search.component.html',
  styleUrls: ['./associate-search.component.css']
})
export class AssociateSearchComponent implements OnInit {
  associates$!: Observable<Associate[]>;
  private searchTerms = new Subject<string>();

  constructor(private associateService: AssociateService) {}

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.associates$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.associateService.searchAssociates(term)),
    );
  }
}