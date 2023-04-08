import {Pipe, PipeTransform} from '@angular/core';
import {ICategory} from "../models/category";

@Pipe({
  name: 'filterCategories'
})
export class FilterCategoriesPipe implements PipeTransform {

  transform(categories: ICategory[], search: string): ICategory[] {
    if (search.length == 0)
      return categories;

    return categories.filter(c => c.title && c.title.toLowerCase().includes(search.toLowerCase()))
  }

}
