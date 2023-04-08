import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../../services/authentication.service";
import {IAccount} from "../../../models/account";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {


  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
  }

  message: any

  form = new FormGroup({
    firstName: new FormControl<string>('', [
      Validators.required
    ]),
    secondName: new FormControl<string>('', [
      Validators.required
    ]),
    username: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(5)
    ]),
    password: new FormControl<string>('', [
      Validators.required
    ])
  })

  register() {
    this.authenticationService.register(<IAccount>{
      firstName: this.form.value.firstName,
      secondName: this.form.value.secondName,
      username: this.form.value.username,
      password: this.form.value.password,
    }).subscribe({
      next: (value) => this.onSuccess(value),
      error: (value) => this.onFailure(value.error)
    })
  }

  onSuccess(response: any) {
    console.log("success!")
    let username = this.form.value.username
    let password = this.form.value.password

    this.authenticationService.authenticate(username, password)
      .subscribe({
        next: () => this.toProfilePage(username)
      })
  }

  onFailure(error: any) {
    this.message = error.message
  }


  toProfilePage(username: any) {
    this.router.navigate(['/' + username]).then(() => console.log('redirect'))
  }
}
