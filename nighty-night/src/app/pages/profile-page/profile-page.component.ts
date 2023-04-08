import {Component, OnInit} from '@angular/core';
import {IProfile} from "../../models/profile";
import {ProfileService} from "../../services/profile.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  username: string
  profile: IProfile

  constructor(private profileService : ProfileService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.url.subscribe(url => {
      this.username = url[0].path
      this.getProfile()
    })
  }

  getProfile() {
    this.profileService.getProfile(this.username).subscribe(value => {
      this.profile = value
    })
  }
}
