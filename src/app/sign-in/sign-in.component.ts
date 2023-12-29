import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  loginStatus:any=sessionStorage.getItem("loginStatus");

  constructor(private service:UserService, private router:Router)
  {
  }
  
  signin(email:any, password:any)
  {
    this.service.signin(email,password).subscribe(
      response => {
        console.log(response);
        alert('login successful');
        sessionStorage.setItem("loginStatus","active");
        sessionStorage.setItem("userId",response.userId);
        sessionStorage.setItem("designation",response.designation);
        this.goTo();
    },
    () => { alert('Wrong email Id or password!!');  }
    );
  }
  signUp(){
    this.router.navigate(['signup'])
  }

  goTo(){
   
      this.router.navigate(['books'])
   
  }
}
