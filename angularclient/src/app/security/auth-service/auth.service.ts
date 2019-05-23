import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AuthService {

  url: string;
  adminRole: boolean;

  constructor(
    private http: HttpClient
  ) {
    this.url = 'http://localhost:8080/api/users/admin';
  }

  public getToken(): string {
    return sessionStorage.getItem('token');
  }

  public isAuthenticated() {
    return this.getToken() !== null && this.getToken() !== '';
  }

  public hasAdminRole() {
   this.http.get<boolean>(this.url).subscribe(res => this.adminRole = res);
   return this.adminRole;
  }

}
