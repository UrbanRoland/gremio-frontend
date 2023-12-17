import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from 'src/app/shared/AuthResponse';
import { AuthRequest } from 'src/app/shared/AuthRequest';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LocalStorageService } from './localStorage.service';
import { RegistrationRequest } from '../shared/RegistrationRequest';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000';
  constructor(
    private http: HttpClient,
    public jwtHelper: JwtHelperService,
    private localStorage: LocalStorageService
  ) {}

  public login(data: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.apiUrl+"/login", data);
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('accessToken');

    // Check whether the token is expired and return
    // true or false

    return !this.jwtHelper.isTokenExpired(token);
  }

public registration(data: RegistrationRequest): Observable<RegistrationRequest> {
  return this.http.post<RegistrationRequest>(this.apiUrl+"/api/auth", data);
}


  public logOut() {
    this.localStorage.remove('accessToken');
  }
}
