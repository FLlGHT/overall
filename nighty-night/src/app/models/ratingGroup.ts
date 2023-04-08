import {ICategoryGroup} from "./categoryGroup";
import {IRating} from "./rating";

export interface IRatingGroup {
  id: number,
  title: string,
  categoryGroup: ICategoryGroup,
  description: string,
  color: string,
  groupRating: number,
  ratings: IRating[]
}
