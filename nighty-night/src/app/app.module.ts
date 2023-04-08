import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {MatSliderModule} from '@angular/material/slider';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {CategoryComponent} from "./components/category/category.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {GlobalErrorComponent} from './components/global-error/global-error.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FilterCategoriesPipe} from './pipes/filter-categories.pipe';
import {ModalComponent} from './components/modal/modal.component';
import {CreateCategoryComponent} from './components/create-category/create-category.component';
import {FocusDirective} from './directives/focus.directive';
import {ProfilePageComponent} from './pages/profile-page/profile-page.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {ContactsPageComponent} from './pages/contacts-page/contacts-page.component';
import {SettingsPageComponent} from './pages/settings-page/settings-page.component';
import {AdminPageComponent} from './pages/admin/admin-page/admin-page.component';
import {LoginPageComponent} from './pages/auth/login-page/login-page.component';
import {RegistrationPageComponent} from './pages/auth/registration-page/registration-page.component';
import {FooterComponent} from './components/footer/footer.component';
import {AdminCategoriesPageComponent} from './pages/admin/admin-categories-page/admin-categories-page.component';
import {
  AdminCategoryGroupsPageComponent
} from './pages/admin/admin-category-groups-page/admin-category-groups-page.component';
import {CategoryGroupComponent} from './components/category-group/category-group.component';
import {CreateCategoryGroupComponent} from './components/create-category-group/create-category-group.component';
import {ProfileHeaderComponent} from './components/profile-header/profile-header.component';
import {ProfileContactsComponent} from './components/profile-contacts/profile-contacts.component';
import {ProfileRatingsComponent} from './components/profile-ratings/profile-ratings.component';
import {ProfileImageComponent} from './components/profile-image/profile-image.component';
import {RatingSliderComponent} from './components/rating-slider/rating-slider.component';
import {FilterCategoryGroupsPipe} from './pipes/filter-category-groups.pipe';
import {ProfileSummaryComponent} from './components/profile-summary/profile-summary.component';
import { OverallChartComponent } from './components/overall-chart/overall-chart.component';
import { HomePageComponent } from './pages/home/home-page.component';
import {AppService} from "./services/app.service";
import {InterceptorService} from "./services/interceptor.service";
import {MatSelectModule} from '@angular/material/select';
import { PrettySidebarComponent } from './components/pretty-sidebar/pretty-sidebar.component';
import {MatTabsModule} from '@angular/material/tabs'
import {BrowserAnimationsModule, provideAnimations} from '@angular/platform-browser/animations';
import { ProfileInfoComponent } from './components/profile-info/profile-info.component';


@NgModule({
    declarations: [
        AppComponent,
        CategoryComponent,
        GlobalErrorComponent,
        FilterCategoriesPipe,
        ModalComponent,
        CreateCategoryComponent,
        FocusDirective,
        ProfilePageComponent,
        NavigationComponent,
        ContactsPageComponent,
        SettingsPageComponent,
        AdminPageComponent,
        LoginPageComponent,
        RegistrationPageComponent,
        FooterComponent,
        AdminCategoriesPageComponent,
        AdminCategoryGroupsPageComponent,
        CategoryGroupComponent,
        CreateCategoryGroupComponent,
        ProfileHeaderComponent,
        ProfileContactsComponent,
        ProfileRatingsComponent,
        ProfileImageComponent,
        RatingSliderComponent,
        FilterCategoryGroupsPipe,
        ProfileSummaryComponent,
        OverallChartComponent,
        HomePageComponent,
        PrettySidebarComponent,
        ProfileInfoComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatSelectModule,
    MatTabsModule,
    BrowserAnimationsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
