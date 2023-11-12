import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, finalize, tap } from 'rxjs';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm!: FormGroup;
  submitted = false;
  errorMessage?: string;
  loading = false;

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
    if (this.forgotPasswordForm.valid && !this.loading) {
      this.loading = true;

      this.userService
        .forgotPassword(this.forgotPasswordForm.value)
        .pipe(
          tap((response) => {
            this.toggleModal();
          }),
          catchError((error) => {
            this.errorMessage = error.error.message;
            throw error;
          }),
          finalize(() => {
            this.loading = false;
          })
        )
        .subscribe();
    }
  }

  toggleModal() {
    const dropdown = document.querySelector('.modal');

    if (dropdown) {
      dropdown.classList.toggle('hidden');
    }
    this.loading = false;
  }
}
