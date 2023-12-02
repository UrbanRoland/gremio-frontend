import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  public constructor(private authService: AuthService) {}

  toggleDropdown() {
    const dropdown = document.querySelector('.dropdown-menu');

    if (dropdown) {
      if (dropdown.classList.contains('hidden')) {
        dropdown.classList.remove('hidden');
      } else {
        dropdown.classList.add('hidden');
      }
    }
  }

  logOut() {
    this.authService.logOut();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }
}
