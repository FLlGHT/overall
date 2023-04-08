import {IProfile} from "./profile";
import {IContact} from "./contact";
import {IFilter} from "./filter";

export interface ContactsInfo {
  profile? : IProfile,
  contacts?: IContact[],
  filter?: IFilter
}
