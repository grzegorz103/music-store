import { Injectable } from '@angular/core';
import { Order } from '../model/order';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable()
export class OrderService {

  private orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderUrl = 'http://localhost:8080/api/order';
  }

  public findAll() {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    let options = { headers: headers };

    return this.http.get<Order[]>(this.orderUrl, options);
  }

}
