import { Component } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  userId: any;

  constructor(private service: UserService, private router: Router) {}

  user: any;

  saveUser(username: any, email: any, password: any, repassword: any, designation: any) {
    if (password === repassword) {
      this.user = new User(this.userId, username, password, email, designation); // Use the constant here
      console.log(this.user);

      this.service.signup(this.user).subscribe(
        (response) => {
          console.log(response);
          alert('Signup successful!!');
          this.router.navigate(['/sign-in']);
        },
        (error) => {
          alert(error.message);
        }
      );
    } else {
      alert('Password does not match!!');
      window.location.reload();
    }
  }
}

