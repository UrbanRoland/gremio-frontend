import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { AuthRequest } from 'src/app/shared/AuthRequest';
import { AuthResponse } from 'src/app/shared/AuthResponse';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  authResp!: AuthResponse;
  constructor(private auth: AuthenticationService) {}

  submitLogin(loginData: AuthRequest) {
    this.auth.login(loginData).subscribe(
      (response) => {
        console.log('Response:', response);
        this.authResp = response;
      },
      (error) => {
        console.error('Error:', error);
      }
    );
  }
}
