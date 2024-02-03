import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthResponse } from 'src/app/shared/AuthResponse';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LocalStorageService } from './localStorage.service';
import { RegistrationRequest } from '../shared/RegistrationRequest';
import { Apollo, MutationResult, gql } from 'apollo-angular';
import LOGIN_MUTATION from '../graphql/mutations/login';


@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:9000/graphql';
  constructor(
    private http: HttpClient,
    public jwtHelper: JwtHelperService,
    private localStorage: LocalStorageService,
    private apollo: Apollo
  ) {}

  login(email: string, password: string): Observable<MutationResult> {
    return this.apollo
      .mutate<AuthResponse>({
        mutation: LOGIN_MUTATION,
        variables: { email, password },
      });
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
