import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, tap } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm!: FormGroup;
  submitted = false;
  errorMessage?: string

  public constructor(
    private fb: FormBuilder,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.forgotPasswordForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSumbit() {
    this.submitted = true;
    if (this.forgotPasswordForm.valid) {
      this.userService
        .forgotPassword(this.forgotPasswordForm.value)
        .pipe(
          tap((response) => {
            console.log('Response: ', response);

            const dropdown = document.querySelector('.modal');

            if (dropdown) {
              if (dropdown.classList.contains('hidden')) {
                dropdown.classList.remove('hidden');
              } else {
                dropdown.classList.add('hidden');
              }
            }
          }),
          catchError((error) => {
            console.log('Error: ', JSON.stringify(error));
            this.errorMessage = error.error.message
            throw error;
          })
        )
        .subscribe();
    }
  }

  closeDropdown() {
    const dropdown = document.querySelector('.modal');

    if (dropdown) {
      if (dropdown.classList.contains('hidden')) {
        dropdown.classList.remove('hidden');
      } else {
        dropdown.classList.add('hidden');
      }
    }
  }
}
