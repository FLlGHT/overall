import {Component, Input, OnInit} from "@angular/core";
import {ICategory} from "../../models/category";
import {ICategoryGroup} from "../../models/categoryGroup";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit {
  @Input() category: ICategory
  @Input() groups: ICategoryGroup[]
  form: FormGroup
  hasChanges = false

  constructor(public formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm() {
    this.form = this.formBuilder.group({
      title: [this.category.title],
      categoryGroup: [this.category.categoryGroup],
      categoryType: [this.category.categoryType],
      weight: [this.category.weight],
      description: [this.category.description]
    })
  }

  onChanges() {
    this.hasChanges = true
  }

  onUpdate() {

  }

  onDelete() {

  }

}
