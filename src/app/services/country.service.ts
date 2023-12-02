import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Country } from '../shared/country';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CountryService {
  private apiUrl = 'https://restcountries.com/v3.1/all';

  constructor(private http: HttpClient) {}


  fetchCountries(): Observable<Country[]> {
    return this.http.get<Country[]>(this.apiUrl).pipe(
      catchError((error) => {
        console.error('Error fetching countries:', error);
        return throwError('Something went wrong');
      })
    );
  }
}
