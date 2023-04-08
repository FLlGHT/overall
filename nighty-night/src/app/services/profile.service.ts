import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {IProfile} from "../models/profile";
import {HttpClient} from "@angular/common/http";
import {IRating} from "../models/rating";
import {AuthenticationService} from "./authentication.service";
import {IRatingGroup} from "../models/ratingGroup";
import {RatingsUpdate} from "../models/ratingsUpdate";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  url: string = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) {
  }

  getProfile(username: string): Observable<IProfile> {
    return this.httpClient.get<IProfile>(this.url + '/' + username)
  }

  updateRatings(rating: IRating, ratingGroup: IRatingGroup, profile: IProfile): Observable<RatingsUpdate> {
    let requestBody : RatingsUpdate = {
      rating: rating,
      ratingGroup: ratingGroup,
      profile: profile
    }

    return this.httpClient.post<RatingsUpdate>(this.url + '/ratings/update', requestBody)
  }
}
