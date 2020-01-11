import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../../model/user';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class UserService {

  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl =  environment.apiUrl +'/api/users/';
  }

  public save(user: User) {
    sessionStorage.setItem('token', '');
    return this.http.post<User>(this.usersUrl + 'register', user);
  }

  public findAll() {
    return this.http.get<User[]>(this.usersUrl);
  }

  public findCurrentUser() {
    return this.http.get<User>(this.usersUrl + 'curr');
  }

  public delete(id: number) {
    return this.http.delete<User>(this.usersUrl + id);
  }

  public update(id: number, user: User){
    return this.http.put<User>(this.usersUrl + id, user);
  }

}
