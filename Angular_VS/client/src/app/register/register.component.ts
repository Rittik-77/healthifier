import { AuthService } from './../services/auth/auth.service';
import { BaseHttpService } from './../services/http/base-http.service';
import { User } from './../models/user/user';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as Constants from '../app.constants';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  userRegForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    username: new FormControl('', Validators.required),
    weight: new FormControl('', Validators.required)
  });

  get email() {
    return this.userRegForm.get('email')
  }

  get username() {
    return this.userRegForm.get('username')
  }

  get password() {
    return this.userRegForm.get('password')
  }

  get weight() {
    return this.userRegForm.get('weight')
  }

  ngOnInit() {
  }

  onSubmit() {
    // Construct the user object
    let user = new User(this.email.value, this.password.value, this.username.value, this.weight.value, Constants.ROLE_USER);

    // Make the http call
    this.authService.register(user)
      .subscribe(response => {
        if (response)
          this.router.navigate([Constants.LOGIN], { queryParams: {'toast' : Constants.REG_SUCCESS_MSG}})
      }, error => {
          console.log(error)
      });
  }

}
