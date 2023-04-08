import {Component, Input} from '@angular/core';
import {IProfile} from "../../models/profile";
import {ICategoryGroup} from "../../models/categoryGroup";
import {IRatingGroup} from "../../models/ratingGroup";

@Component({
  selector: 'app-profile-summary',
  templateUrl: './profile-summary.component.html',
  styleUrls: ['./profile-summary.component.css']
})
export class ProfileSummaryComponent {

  @Input() profile: IProfile


  groupStyle(ratingGroup: IRatingGroup) : string {
    return 'star col ' + ratingGroup.color;
  }
}
