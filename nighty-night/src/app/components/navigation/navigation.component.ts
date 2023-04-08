import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {


  constructor(private route: ActivatedRoute, private router: Router, public authenticationService: AuthenticationService) {

  }

  ngOnInit(): void {
  }

  isLoggedIn() {
    return this.authenticationService.isUserLoggedIn()
  }

  logout() {
    return this.authenticationService.logout();
  }
}
