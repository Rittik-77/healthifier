import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { MainComponent } from './main/main.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AboutComponent } from './about/about.component';
import { WelcomeComponent } from './welcome/welcome.component';

import { BaseHttpService } from './services/http/base-http.service';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FoodTrackerComponent } from './food-tracker/food-tracker.component';
import { WorkoutTrackerComponent } from './workout-tracker/workout-tracker.component';
import { SummaryComponent } from './summary/summary.component';
import { NotifyComponent } from './notify/notify.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { TokenHttpService } from './services/http/token-http.service';
import { FoodTrackerAddFormComponent } from './food-tracker-add-form/food-tracker-add-form.component';
import { FoodTrackerEditFormComponent } from './food-tracker-edit-form/food-tracker-edit-form.component';
import { WorkoutTrackerAddFormComponent } from './workout-tracker-add-form/workout-tracker-add-form.component';
import { WorkoutTrackerEditFormComponent } from './workout-tracker-edit-form/workout-tracker-edit-form.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    SidebarComponent,
    AboutComponent,
    WelcomeComponent,
    LoginComponent,
    RegisterComponent,
    FoodTrackerComponent,
    WorkoutTrackerComponent,
    SummaryComponent,
    NotifyComponent,
    PageNotFoundComponent,
    FoodTrackerAddFormComponent,
    FoodTrackerEditFormComponent,
    WorkoutTrackerAddFormComponent,
    WorkoutTrackerEditFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    BaseHttpService,
    TokenHttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
