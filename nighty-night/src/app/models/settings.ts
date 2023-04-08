import {IAccount} from "./account";
import {IProfile} from "./profile";

export interface ISettings {
  id? : number,
  account : IAccount,
  profile : IProfile,
  closedProfile : boolean,
  closedGrades : boolean
}
