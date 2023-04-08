import {Component, OnInit} from '@angular/core';
import {CategoriesService} from "../../../services/categories.service";
import {ModalService} from "../../../services/modal.service";
import {ICategoryGroup} from "../../../models/categoryGroup";

@Component({
  selector: 'app-admin-category-groups-page',
  templateUrl: './admin-category-groups-page.component.html',
  styleUrls: ['./admin-category-groups-page.component.css']
})
export class AdminCategoryGroupsPageComponent implements OnInit {

  loading = false
  filterString: string = ''

  categoryGroups: ICategoryGroup[]

  constructor(
    public categoriesService: CategoriesService,
    public modalService: ModalService) {
  }

  ngOnInit(): void {
    this.loading = true
    this.categoriesService.getCategoryGroups().subscribe(() => {
      this.loading = false
    })
  }
}
