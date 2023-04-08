import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {DomSanitizer} from "@angular/platform-browser";
import {IProfile} from "../../models/profile";

@Component({
  selector: 'app-profile-image',
  templateUrl: './profile-image.component.html',
  styleUrls: ['./profile-image.component.css']
})
export class ProfileImageComponent implements OnInit {

  @Input() profile: IProfile
  base64 : any
  image : any

  constructor() {
  }

  imageIsPresent(): boolean {
    return this.profile && this.profile.image && this.profile.image.content
  }

  ngOnInit(): void {

  }

  getImage() {
    this.base64 = this.profile.image.content
    return 'data:image/jpeg;base64,' + this.base64
  }
}
