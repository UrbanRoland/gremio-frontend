import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AuthGuardService } from 'src/app/services/auth-guard.service';
import { JwtModule } from '@auth0/angular-jwt';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { IconComponent } from './components/icon/icon.component';
import { GraphQLModule } from './graphql.module';



const jwtOptions = {
  // Your JWT options configuration here (e.g., tokenGetter, whitelistedDomains, etc.)
};

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    UserSettingsComponent,
    DashboardComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    IconComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    JwtModule.forRoot({
      config: jwtOptions,
    }),
    GraphQLModule,
  ],
  providers: [
    AuthGuardService,
 
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
