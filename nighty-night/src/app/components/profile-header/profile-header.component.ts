import {Component, EventEmitter, Input, Output} from '@angular/core';
import {IProfile} from "../../models/profile";

@Component({
  selector: 'app-profile-header',
  templateUrl: './profile-header.component.html',
  styleUrls: ['./profile-header.component.css']
})
export class ProfileHeaderComponent {

  @Input() profile: IProfile;

}
