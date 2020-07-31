import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { WorkoutTrackerComponent } from './workout-tracker/workout-tracker.component';
import { FoodTrackerComponent } from './food-tracker/food-tracker.component';
import { LoginComponent } from './login/login.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { AboutComponent } from './about/about.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { SummaryComponent } from './summary/summary.component';
import { NotifyComponent } from './notify/notify.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'welcome',
    pathMatch: 'full'
  },
  {
    path: 'about',
    component: AboutComponent
  },
  {
    path: 'welcome',
    component: WelcomeComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'foodTracker',
    component: FoodTrackerComponent
  },
  {
    path: 'workoutTracker',
    component: WorkoutTrackerComponent
  },
  {
    path: 'summary',
    component: SummaryComponent
  },
  {
    path: 'notify',
    component: NotifyComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
