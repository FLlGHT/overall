import { Component } from '@angular/core';
import {AppService} from "../../services/app.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {IAccount} from "../../models/account";
import {AuthenticationService} from "../../services/authentication.service";

@Component({
  selector: 'app-home',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {

  text : string = 'Have a good time. Life is too short to get bogged down and be discouraged. You have to keep' +
    '  moving. You have to keep going. Put one foot in front of the other, smile and just keep on rolling.'

  constructor(private authenticationService: AuthenticationService) {

  }
  isAuthenticated() {
    return this.authenticationService.isUserLoggedIn()
  }

  getUsername() {
    return this.authenticationService.getLoggedInUserName();
  }
}
