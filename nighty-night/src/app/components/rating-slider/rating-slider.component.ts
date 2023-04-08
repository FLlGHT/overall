import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IRating} from "../../models/rating";
import {ProfileService} from "../../services/profile.service";
import {style} from "@angular/animations";
import {IProfile} from "../../models/profile";
import {IRatingGroup} from "../../models/ratingGroup";

@Component({
  selector: 'app-rating-slider',
  templateUrl: './rating-slider.component.html',
  styleUrls: ['./rating-slider.component.css']
})
export class RatingSliderComponent {

  @Input() rating: IRating
  @Input() ratingGroup: IRatingGroup
  @Input() profile: IProfile

  value = 0

  constructor(private profileService: ProfileService) {
  }

  sliderStyle(): string {
    return 'background: linear-gradient(to right, #6b8dff 0%, #061a5e '
      + this.rating.grade.previousGrade + '%, #fff ' + this.rating.grade.previousGrade + '%, #fff 100%)'
  }

  updateSlider(slider: any) {
    let value = slider.value
    this.value = value

    slider.style.background = 'linear-gradient(to right, #6b8dff 0%, #061a5e ' + value + '%, #fff ' + value + '%, #fff 100%)'
  }

  saveGrade() {
    this.rating.grade.currentGrade = this.value
    this.profileService.updateRatings(this.rating, this.ratingGroup, this.profile).subscribe(response => {
      console.log(response)

      this.updateRating(response.rating)
      this.updateRatingGroup(response.ratingGroup)
      this.updateOverall(response.profile.overallRating)
    })
  }

  updateRating(response: IRating) {
    this.rating.id = response.id
    this.rating.rating = response.rating
    this.rating.grade = response.grade
  }

  updateRatingGroup(response: IRatingGroup) {
    this.ratingGroup.groupRating = response.groupRating
    this.ratingGroup.id = response.id
  }

  updateOverall(response: number) {
    this.profile.overallRating = response
  }


  valueLabel() : number {
    if (this.value === 0)
      return this.rating.grade.currentGrade

    return this.value
  }
}
