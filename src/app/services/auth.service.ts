import { Injectable } from '@angular/core';
import { AuthResponse } from 'src/app/shared/AuthResponse';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { LocalStorageService } from './localStorage.service';
import { Apollo, MutationResult } from 'apollo-angular';
import LOGIN_MUTATION from '../graphql/mutations/login';
import { UserInput } from '../shared/inputs/UserInput';
import { REGISTRATION_MUTATION } from '../graphql/mutations/registration';
import { User } from '../shared/model/User';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    public jwtHelper: JwtHelperService,
    private localStorage: LocalStorageService,
    private apollo: Apollo
  ) {}

 public login(email: string, password: string): Observable<MutationResult> {
    return this.apollo.mutate<AuthResponse>({
      mutation: LOGIN_MUTATION,
      variables: { email, password },
    });
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('accessToken');

    //todo
    // Check whether the token is expired and return
    // true or false

    return !this.jwtHelper.isTokenExpired(token);
  }

 public registration(user: UserInput): Observable<MutationResult> {
    return this.apollo.mutate<User>({
      mutation: REGISTRATION_MUTATION,
      variables: { user },
    });
  }

  public logOut() {
    this.localStorage.remove('accessToken');
  }
}
