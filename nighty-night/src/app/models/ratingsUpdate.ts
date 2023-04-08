import {IRating} from "./rating";
import {IRatingGroup} from "./ratingGroup";
import {IProfile} from "./profile";

export interface RatingsUpdate {
  rating: IRating,
  ratingGroup: IRatingGroup,
  profile: IProfile
}
