import {Component, Input} from '@angular/core';
import {IProfile} from "../../models/profile";

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {

  @Input() profile : IProfile



}
