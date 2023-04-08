import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IFilter} from "../models/filter";
import {Observable} from "rxjs";
import {ContactsInfo} from "../models/contactsInfo";

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  url: string = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) {

  }

  getContacts(username: string, filter: IFilter | undefined): Observable<ContactsInfo> {
    let requestBody: ContactsInfo = {
      filter: filter
    }

    return this.httpClient.post<ContactsInfo>(this.url + '/' + username + '/contacts', requestBody)
  }


}
