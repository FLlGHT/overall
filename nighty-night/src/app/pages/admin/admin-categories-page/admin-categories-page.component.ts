import { Component } from '@angular/core';
import {CategoriesService} from "../../../services/categories.service";
import {ModalService} from "../../../services/modal.service";
import {ICategory} from "../../../models/category";
import {ICategoryGroup} from "../../../models/categoryGroup";

@Component({
  selector: 'app-admin-categories-page',
  templateUrl: './admin-categories-page.component.html',
  styleUrls: ['./admin-categories-page.component.css']
})
export class AdminCategoriesPageComponent {

  filterString: string = ''

  categories : ICategory[]

  groups: ICategoryGroup[]

  constructor(
    public categoriesService: CategoriesService,
    public modalService: ModalService) {
  }

  ngOnInit(): void {
    this.categoriesService.getCategories().subscribe(response => {
      this.categories = response.categories
      this.groups = response.groups
    })
  }

}
