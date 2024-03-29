import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { LocalStorageService } from 'src/app/services/localStorage.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMessage?: string;
  showPassword: boolean = false;

  constructor(
    private auth: AuthService,
    private localStorage: LocalStorageService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onSumbit() {
    this.submitted = true;
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')?.value;
      const password = this.loginForm.get('password')?.value;

      this.auth
        .login(email, password)
        .pipe(
          tap((response) => {
            console.log('Response:', response);
            this.localStorage.set('accessToken', response.data.login.accessToken);
            this.router.navigate(['dashboard']);
          }),
          catchError((error) => {
            console.error('Error:', error);
            this.errorMessage = error.error;
            throw error;
          })
        )
        .subscribe();
    }
  }

  isAuthenticated(): boolean {
    return this.auth.isAuthenticated();
  }

  showHidePassword(event: Event) {
    event.preventDefault();
    this.showPassword = !this.showPassword;
  }
  
}
