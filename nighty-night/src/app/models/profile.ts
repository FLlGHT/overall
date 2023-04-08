import {IRatingGroup} from "./ratingGroup";
import {IContact} from "./contact";


export interface IProfile {
  id? : number,
  firstName : string,
  lastName : string,
  username : string,
  dateOfBirth : any,
  placeOfResidence : string,
  overallRating: number,
  description: string,
  email: string,
  role: string,
  numberOfContacts: number,
  canAddToContacts: boolean,
  company: ICompany,
  externalLinks: IExternalLink[],
  contacts: IContact[],
  ratingGroups: IRatingGroup[],
  image : any,
  uploadedFile: any
}

export interface ICompany {
  id?: number,
  name: string
}

export interface IExternalLink {
  id?: number,
  title?: string,
  link: string
}

