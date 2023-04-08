import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../../services/authentication.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {


  errorMessage = 'Invalid Credentials';
  successMessage: 'Login Successful';
  invalidLogin = false;
  loginSuccess = false;

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private httpClient: HttpClient) {

  }

  form = new FormGroup({
    username: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    password: new FormControl<string>('', [
      Validators.required
    ])
  })

  login() {
    let username = this.form.value.username
    let password = this.form.value.password
    this.authenticationService.authenticate(username, password )
      .subscribe({
        next: () => this.onSuccess(username),
        error: () => this.onFailure()
      })
  }

  onSuccess(username: any) {
    this.invalidLogin = false;
    this.loginSuccess = true;
    this.router.navigate(['/' + username]).then(() => console.log('redirect'))
  }

  onFailure() {
    this.invalidLogin = true;
    this.loginSuccess = false;
  }
}
