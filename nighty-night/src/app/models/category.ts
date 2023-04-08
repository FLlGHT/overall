import {ICategoryGroup} from "./categoryGroup";


export interface ICategory {
  id?: number,
  title?: string,
  description?: string,
  categoryType? : string,
  categoryGroup: ICategoryGroup,
  weight: number
}

export interface ICategories {
  categories: ICategory[],
  groups: ICategoryGroup[]
}

