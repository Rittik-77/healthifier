import { AuthService } from './../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { User } from '../models/user/user';
import { ActivatedRoute, Router } from '@angular/router';
import * as Constants from '../app.constants';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  showToastMessage: string
  loginResponse: string
  loginSuccess: boolean
  loginFailure: boolean

  userLoginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });

  get email() {
    return this.userLoginForm.get('email')
  }

  get password() {
    return this.userLoginForm.get('password')
  }

  ngOnInit() {

    this.showToastMessage = null
    this.loginResponse = null
    this.loginSuccess = null
    this.loginFailure = null

    // if redirected from register page, display the toast
    if (this.activatedRoute && this.activatedRoute.queryParams && this.activatedRoute.queryParams['value']) {
      if (this.activatedRoute.queryParams['value']['toast'] === Constants.REG_SUCCESS_MSG)
        this.showToastMessage = Constants.REG_SUCCESS_MSG
    }
  }

  onSubmit() {

    this.showToastMessage = null
    this.loginResponse = null
    this.loginSuccess = null
    this.loginFailure = null

    // Construct the user object
    let user = new User(this.email.value, this.password.value);

    // Make the http call
    this.authService.login(user)
      .subscribe(response => {
        localStorage.setItem('token', response as string)
        // Navigate to my summary page with success toast
        this.loginResponse = Constants.LOGIN_SUCCESS_MSG
        this.loginSuccess = true
        this.userService.getLoggedUsername()
          .subscribe(response => {
            localStorage.setItem('username', response as string)
          }, error => console.log(error))
        setTimeout(() => {
          this.router.navigateByUrl(Constants.SUMMARY)
        }, 1500);
      }, error => {
        // Display the failure toast
        this.loginFailure = true
        this.loginResponse = Constants.LOGIN_FAIL_MSG + error.error.uiMessage
      });
  }

}
