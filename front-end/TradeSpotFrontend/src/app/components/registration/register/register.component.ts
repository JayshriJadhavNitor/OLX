import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { register } from 'src/app/models/register';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private service:AuthService, private router: Router) {
    
  }
  
  register(data:register) {
    // console.log(data);

    this.service.register(data).subscribe((result) => {
      console.log(result);
      this.router.navigateByUrl("/login");
    })
    
  }

  
}
