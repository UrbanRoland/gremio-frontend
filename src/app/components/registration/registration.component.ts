import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm!: FormGroup;
  submitted = false;
  errorMessage?: string;
  showPassword: boolean = false;

  constructor(
    private auth: AuthService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,  Validators.minLength(5)]],
      firstName: [''],
      lastName: [''],
    });
  }

  onSumbit() {
    this.submitted = true;
    if (this.registrationForm.valid) {
      this.auth
        .registration(this.registrationForm.value)
        .pipe(
          tap((response) => {
            console.log('Response:', response);
            this.router.navigate(['login']);
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