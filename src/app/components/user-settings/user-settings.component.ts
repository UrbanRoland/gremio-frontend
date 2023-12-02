import { Component, OnInit } from '@angular/core';
import { CountryService } from 'src/app/services/country.service';
import { Country } from 'src/app/shared/country';
import { catchError, map } from 'rxjs/operators';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css'],
})
export class UserSettingsComponent implements OnInit {
  countries: Country[] = [];
  userSettings!: FormGroup;

  constructor(private countryApi: CountryService, private fb: FormBuilder) {}

  ngOnInit() {
    this.loadCountries();
    this.userSettings = this.fb.group({
      username: ['', Validators.required],
    });
  }

  loadCountries() {
    this.countryApi
      .fetchCountries()
      .pipe(
        map((data) => {
          return data.sort((a, b) =>
            a.name.common.localeCompare(b.name.common)
          );
        }),
        catchError((error) => {
          console.error('Error fetching countries:', error);
          return [];
        })
      )
      .subscribe((sortedCountries) => {
        this.countries = sortedCountries;
      });
  }

  submit(userSettings: FormGroup) {
    console.log('Valid?', userSettings.valid);
    console.log('Name', userSettings.value.username);
  }
}
