import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailRequest } from '../shared/EmailRequest';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:9000/users';

  constructor(private http: HttpClient) {}

  public forgotPassword(data: EmailRequest): Observable<EmailRequest> {
    return this.http.post<EmailRequest>(
      this.baseUrl + '/forgot-password',
      data
    );
  }
  public resetPassword(password: string, token: string): Observable<EmailRequest> {
    const data = { password, token };
    return this.http.post<EmailRequest>(this.baseUrl + '/reset-password', data);
  }
}
