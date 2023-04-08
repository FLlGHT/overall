import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {IAccount} from "../models/account";

@Injectable({
  providedIn: 'root'
})
export class AppService {


  constructor(private http: HttpClient) {
  }

  // authenticate(credentials: any, callback: any) {
  //   if (credentials)
  //     console.log('credentials from form: ' + credentials.username + ' ' + credentials.password)
  //
  //   const headers = new HttpHeaders(credentials ? {
  //     authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
  //   } : {});
  //
  //   let account = credentials ? {username: credentials.username, password: credentials.password} : {}
  //
  //   this.http.post<IAccount>(this.url + '/auth', account, {headers: headers})
  //     .subscribe(response => {
  //       console.log('response after auth, username: ' + response.username)
  //       this.authenticated = !!response.username;
  //       return callback && callback();
  //     });
  //
  // }


}
