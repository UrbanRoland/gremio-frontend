import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from 'src/app/shared/AuthResponse';
import { AuthRequest } from 'src/app/shared/AuthRequest';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LocalStorageService } from './localStorage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000/login';
  constructor(
    private http: HttpClient,
    public jwtHelper: JwtHelperService,
    private localStorage: LocalStorageService
  ) {}

  public login(data: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.apiUrl, data);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('accessToken');

    // Check whether the token is expired and return
    // true or false

    return !this.jwtHelper.isTokenExpired(token);
  }

  public logOut() {
    this.localStorage.remove('accessToken');
  }
}
