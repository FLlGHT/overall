import {Component, Input} from '@angular/core';
import {IProfile} from "../../models/profile";
import {IContact} from "../../models/contact";

@Component({
  selector: 'app-profile-contacts',
  templateUrl: './profile-contacts.component.html',
  styleUrls: ['./profile-contacts.component.css']
})
export class ProfileContactsComponent {

  @Input() profile: IProfile;

  addToContacts() {
    // add profile to contacts
  }

  imageIsPresent(contact: IContact) {
    return contact && contact.image && contact.image.content
  }

  getImage(contact: IContact) {
    let base64 = contact.image.content
    return 'data:image/jpeg;base64,' + base64
  }
}
