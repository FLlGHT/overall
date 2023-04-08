import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SettingsService} from "../../services/settings.service";
import {ISettings} from "../../models/settings";
import {Router} from "@angular/router";

@Component({
  selector: 'app-settings-page',
  templateUrl: './settings-page.component.html',
  styleUrls: ['./settings-page.component.css']
})
export class SettingsPageComponent implements OnInit {

  imageMessage : any
  image: File
  settings : ISettings
  form : FormGroup

  constructor(private settingsService: SettingsService, public formBuilder: FormBuilder, private router: Router) {
    this.initForm()
  }

  ngOnInit(): void {
    this.settingsService.getSettings().subscribe(response => {
      this.settings = response
      this.updateForm()
    })
  }

  initForm() {
    this.form = this.formBuilder.group({
      image: [null],
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required, Validators.minLength(5)]),
      email: [''],
      dateOfBirth: [''],
      placeOfResidence: [''],
      password: [''],
      company: [''],
      role: [''],
      description: [''],
      closedProfile: [false],
      closedGrades: [false],
      externalLink1: [''],
      externalLink2: [''],
      externalLink3: [''],
    })
  }

  updateForm() {
    this.form.patchValue({
      firstName: this.settings.profile.firstName,
      lastName: this.settings.profile.lastName,
      username: this.settings.profile.username,
      password: this.settings.account.password,
      email: this.settings.profile.email,
      dateOfBirth: this.settings.profile.dateOfBirth,
      placeOfResidence: this.settings.profile.placeOfResidence,
      company: this.settings.profile.company.name,
      role: this.settings.profile.role,
      description: this.settings.profile.description,
      closedProfile: this.settings.closedProfile,
      closedGrades: this.settings.closedGrades,
      externalLink1: this.settings.profile.externalLinks[0].link,
      externalLink2: this.settings.profile.externalLinks[1].link,
      externalLink3: this.settings.profile.externalLinks[2].link
    })
  }

  saveSettings() {
    let profile = this.settings.profile

    this.saveProfileFields()
    this.saveExternalLinks()
    this.savePrivacySettings()

    this.settingsService.saveSettings(this.settings).subscribe(() => {
      this.router.navigate(['/' + profile.username]).then(() => console.log("redirect to profile page"))
    })
  }

  saveProfileFields() {
    let account = this.settings.account
    let profile = this.settings.profile
    let formValues = this.form.value;

    profile.firstName = formValues.firstName
    profile.lastName = formValues.lastName
    profile.username = formValues.username
    account.password = formValues.password
    profile.email = formValues.email
    profile.dateOfBirth = formValues.dateOfBirth
    profile.placeOfResidence = formValues.placeOfResidence
    profile.company = {name: formValues.company}
    profile.role = formValues.role
    profile.description = formValues.description
  }

  saveExternalLinks() {
    let profile = this.settings.profile
    let formValues = this.form.value;

    profile.externalLinks[0].link = formValues.externalLink1
    profile.externalLinks[1].link = formValues.externalLink2
    profile.externalLinks[2].link = formValues.externalLink3
  }

  savePrivacySettings() {
    let formValues = this.form.value;

    this.settings.closedGrades = formValues.closedGrades
    this.settings.closedProfile = formValues.closedProfile
  }

  onImageUpload(event : any) {
    this.image = event.target.files[0]
    this.saveImage()
  }

  saveImage() {
    let imageData = new FormData();
    imageData.append('image', this.image, this.image.name)
    this.settingsService.saveImage(imageData).subscribe(response => {
      this.imageMessage = response.message
    })
  }

  get username() {
    return this.form.controls.username as FormControl
  }
}
