import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";
import {SettingsPageComponent} from "./pages/settings-page/settings-page.component";
import {ContactsPageComponent} from "./pages/contacts-page/contacts-page.component";
import {AdminPageComponent} from "./pages/admin/admin-page/admin-page.component";
import {LoginPageComponent} from "./pages/auth/login-page/login-page.component";
import {RegistrationPageComponent} from "./pages/auth/registration-page/registration-page.component";
import {AdminCategoriesPageComponent} from "./pages/admin/admin-categories-page/admin-categories-page.component";
import {AdminCategoryGroupsPageComponent} from "./pages/admin/admin-category-groups-page/admin-category-groups-page.component";
import {HomePageComponent} from "./pages/home/home-page.component";

const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'home', component: HomePageComponent},
  {path: 'settings', component: SettingsPageComponent},
  {path: 'login', component: LoginPageComponent},
  {path: 'registration', component: RegistrationPageComponent},
  {path: ':username/contacts', component: ContactsPageComponent},
  {path: ':username', component: ProfilePageComponent},
  {path: 'admin/start', component: AdminPageComponent},
  {path: 'admin/categories', component: AdminCategoriesPageComponent},
  {path: 'admin/category-groups', component: AdminCategoryGroupsPageComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
