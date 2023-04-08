import {Component, Input} from '@angular/core';
import { IProfile} from "../../models/profile";
import {IContact} from "../../models/contact";
import {IFilter} from "../../models/filter";
import {ContactsService} from "../../services/contacts.service";
import {ActivatedRoute} from "@angular/router";
import {ICategory} from "../../models/category";

@Component({
  selector: 'app-contacts-page',
  templateUrl: './contacts-page.component.html',
  styleUrls: ['./contacts-page.component.css']
})
export class ContactsPageComponent {
  username: string
  profile: IProfile | undefined
  contacts: IContact[] | undefined
  filter: IFilter = {}

  constructor(private contactService: ContactsService, private route: ActivatedRoute) {
    this.route.url.subscribe(url => {
      this.username = url[0].path
      this.updateContacts()
    })
  }

  applyFilter(categoryId: string) {
    let selectedCategory : ICategory | undefined = this.filter.categories?.filter(value => value.id === parseInt(categoryId))[0]

    if (this.filter)
      this.filter.selectedCategory = selectedCategory

    this.updateContacts()
  }

  updateContacts() {
    this.contactService.getContacts(this.username, this.filter).subscribe(response => {
      this.profile = response.profile
      this.contacts = response.contacts;
      this.filter.categories = response.filter?.categories
    })
  }

  isSelected(category: ICategory): boolean {
    if (!this.filter.selectedCategory)
      return false;

    return category.id == this.filter.selectedCategory.id
  }
}
