import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ISettings} from "../models/settings";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  url = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) {
  }

  getSettings(): Observable<ISettings> {
    return this.httpClient.get<ISettings>(this.url + '/settings')
  }

  saveSettings(settings: ISettings) : Observable<any> {
    return this.httpClient.post<any>(this.url + '/settings/save', settings)
  }

  saveImage(imageData: any) : Observable<any> {
    return this.httpClient.post<any>(this.url + '/image/save', imageData)
  }
}
