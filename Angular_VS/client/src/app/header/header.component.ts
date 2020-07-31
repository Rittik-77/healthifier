import { UserService } from './../services/user/user.service';
import { AuthService } from './../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService,
    private userService: UserService
  ) { }

  ngOnInit() {
  }

  logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    this.router.navigateByUrl("/notify")
  }

  getUsername() {
    if (this.authService.isLoggedIn()) {
      return localStorage.getItem('username')
    }
    return null
  }

}
