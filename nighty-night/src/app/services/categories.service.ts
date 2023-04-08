import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, delay, Observable, retry, tap, throwError} from "rxjs";
import {ICategories, ICategory} from "../models/category";
import {ErrorService} from "./error.service";
import {ICategoryGroup} from "../models/categoryGroup";

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  constructor(private http: HttpClient, private errorService: ErrorService) {
  }

  getCategories(): Observable<ICategories> {
    return this.http.get<ICategories>('http://localhost:8080/categories')
      .pipe(catchError(this.errorHandler.bind(this))
    )
  }

  getCategoryGroups(): Observable<ICategoryGroup[]> {
    return this.http.get<ICategoryGroup[]>('http://localhost:8080/category-groups/all')
      .pipe(catchError(this.errorHandler.bind(this)))
  }

  /*
  createCategory(category: ICategory): Observable<ICategory> {
    return this.http.post<ICategory>('http://localhost:8080/categories/create', category)
      .pipe(
        tap(category => this.categories.push(category))
      )
  }

  createGroup(categoryGroup: ICategoryGroup): Observable<ICategoryGroup> {
    return this.http.post<ICategoryGroup>('http://localhost:8080/category-group/create', categoryGroup)
      .pipe(
        tap(categoryGroup => this.categoryGroups.push(categoryGroup))
      )
  }

   */

  private errorHandler(error: HttpErrorResponse) {
    this.errorService.handle(error.message)
    return throwError(() => error.message)
  }


}
