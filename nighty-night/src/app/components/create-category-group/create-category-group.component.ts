import { Component } from '@angular/core';
import {CategoriesService} from "../../services/categories.service";
import {ModalService} from "../../services/modal.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-category-group',
  templateUrl: './create-category-group.component.html',
  styleUrls: ['./create-category-group.component.css']
})
export class CreateCategoryGroupComponent {

  constructor(
    private categoriesService: CategoriesService,
    private modalService: ModalService
  ) {
  }

  form = new FormGroup({
    title: new FormControl<string>('', [
      Validators.required,
      Validators.minLength(5)
    ])
  })

  get title() {
    return this.form.controls.title as FormControl
  }

  ngOnInit(): void {
  }

  submit() {
  }
}
