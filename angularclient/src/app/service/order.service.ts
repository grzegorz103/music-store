import { Injectable } from '@angular/core';
import { Order } from '../model/order';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class OrderService {

  private orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderUrl = 'http://localhost:8080/api/order';
  }

  public findAll() {
    return this.http.get<Order[]>(this.orderUrl);
  }

}
