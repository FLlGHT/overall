import { Pipe, PipeTransform } from '@angular/core';
import {ICategoryGroup} from "../models/categoryGroup";

@Pipe({
  name: 'filterCategoryGroups'
})
export class FilterCategoryGroupsPipe implements PipeTransform {

  transform(categories: ICategoryGroup[], search: string): ICategoryGroup[] {
    if (search.length == 0) return categories;
    return categories.filter(c => c.title.toLowerCase().includes(search.toLowerCase()))
  }

}
