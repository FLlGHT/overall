import {Component, Input} from '@angular/core';
import {ICategory} from "../../models/category";
import {ICategoryGroup} from "../../models/categoryGroup";

@Component({
  selector: 'app-category-group',
  templateUrl: './category-group.component.html',
  styleUrls: ['./category-group.component.css']
})
export class CategoryGroupComponent {
  @Input() categoryGroup: ICategoryGroup

  details: boolean
}
