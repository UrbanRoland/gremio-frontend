import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Apollo, MutationResult } from 'apollo-angular';
import { FORGOT_PASSWORD_MUTATION } from '../graphql/mutations/forgotPassword';
import { UserInput } from '../shared/inputs/UserInput';
import { RESET_PASSWORD_MUTATION } from '../graphql/mutations/resetPassword';


@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private apollo: Apollo) {}

  public forgotPassword(email: string): Observable<MutationResult> {
    return this.apollo.mutate<string>({
      mutation: FORGOT_PASSWORD_MUTATION,
      variables: { email },
    });
  }

  public resetPassword(userInput: UserInput): Observable<MutationResult> {
    return this.apollo.mutate<string>({
      mutation: RESET_PASSWORD_MUTATION,
      variables: { userInput },
    });
  }
}
