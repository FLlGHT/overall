import {ICategory} from "./category";
import {IGrade} from "./grade";

export interface IRating {
  id: number,
  category: ICategory,
  grade: IGrade,
  rating: number
}


