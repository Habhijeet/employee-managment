
import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credentials = {
    username: '',
    password: ''
  };

  constructor(private userService: UserService, private router: Router) { }

  login() {
    this.userService.login(this.credentials).subscribe(
      data => {
        localStorage.setItem('token', data);
        this.router.navigate(['/employees']);
      },
      error => {
        console.error('Error logging in', error);
      }
    );
  }
}

