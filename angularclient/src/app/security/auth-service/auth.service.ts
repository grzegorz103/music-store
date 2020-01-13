import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthService {

  url: string;

  constructor(
    private http: HttpClient
  ) {
    this.url = environment.apiUrl + '/api/users/admin';
  }

  public getToken(): string {
    return sessionStorage.getItem('token');
  }

  public isAuthenticated() {
    return this.getToken() !== null && this.getToken() !== '';
  }

  public hasAdminRole() {
    return localStorage.getItem('adminRole') !== null && localStorage.getItem('adminRole') === 'true';
  }

  public fetchAdminRole() {
    this.http.get<boolean>(this.url).subscribe(res => localStorage.setItem('adminRole', String(res)));
  }

}
