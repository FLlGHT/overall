import {Component, Input} from '@angular/core';
import {IProfile} from "../../models/profile";
import {IRatingGroup} from "../../models/ratingGroup";
import {IRating} from "../../models/rating";

@Component({
  selector: 'app-profile-ratings',
  templateUrl: './profile-ratings.component.html',
  styleUrls: ['./profile-ratings.component.css']
})
export class ProfileRatingsComponent {

  @Input() profile: IProfile;


  getGroupRating(ratingGroup: IRatingGroup) : string {
    let rating = ratingGroup.groupRating
    return rating === 0 ? '' : 'average: ' + rating
  }

  getRatingIcon(rating: IRating) : string {
    let categoryType = rating.category.categoryType

    if (categoryType === 'IN_DIRECT_RATIO')
      return 'bi bi-arrow-up-short'
    if (categoryType === 'IN_INVERSE_RATIO')
      return 'bi bi-arrow-down-short'
    if (categoryType === 'NOT_AFFECT')
      return 'bi bi-arrow-left-right'

    return ''
  }

  isAuthenticated() : boolean {
    return true
  }
}
