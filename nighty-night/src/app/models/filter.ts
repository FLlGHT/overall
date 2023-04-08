import {ICategory} from "./category";

export interface IFilter {
  selectedCategory ? : ICategory,
  categories ? : ICategory[]
}
