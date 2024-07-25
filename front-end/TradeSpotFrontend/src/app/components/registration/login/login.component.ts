import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms'; // Import NgForm for form submission
import { Router } from '@angular/router';
import { login } from 'src/app/models/login';
import { user } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { token } from 'src/app/models/token';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService ,private router:Router) {}
  user:user|null=null;
  ngOnInit(): void {

  }

  login(data:login){
    this.authService.login(data).subscribe((result:any) => {
      if (result.jwtToken != null) {
        sessionStorage.setItem('jwt', result.jwtToken);
        this.getUserByToken(result);
      }
    })
  }

  getUserByToken(token:token) {
    this.authService.getUser(token).subscribe((result) => {
      if (result != null) {
        
        this.user = result;
        sessionStorage.setItem('user', JSON.stringify(this?.user));
        this.router.navigateByUrl('');
        
      }  
    })
  }
}
