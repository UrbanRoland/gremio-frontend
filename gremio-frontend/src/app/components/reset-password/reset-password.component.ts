import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, tap } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm!: FormGroup;
  submitted = false;
  errorMessage?: string;

  public constructor(
    private fb: FormBuilder,
    private userService: UserService,
    public router: Router
  ) {}

  ngOnInit(): void {
    this.resetPasswordForm = this.fb.group(
      {
        password: ['', [Validators.required, Validators.minLength(5)]],
        passwordAgain: ['', [Validators.required, Validators.minLength(5)]],
      },
      { validator: this.passwordMatchValidator }
    );
  }

  passwordMatchValidator(
    control: AbstractControl
  ): { [key: string]: boolean } | null {
    const password = control.get('password')?.value;
    const passwordAgain = control.get('passwordAgain')?.value;

    // Check if passwords match
    return password === passwordAgain ? null : { passwordMismatch: true };
  }

  onSumbit() {
    this.submitted = true;
    if (this.resetPasswordForm.valid) {
      const { password } = this.resetPasswordForm.value;
      const token = this.extractTokenFromUrl();
      console.log('token' + token);
      this.userService
        .resetPassword(password, token)
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
            this.errorMessage = error.error.message;
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
        this.router.navigate(['login']);
      }
    }
  }

  private extractTokenFromUrl(): string {
    const url = window.location.href;
    const parts = url.split('/');
    return parts[parts.length - 1];
  }
}
