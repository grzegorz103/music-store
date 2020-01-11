import { Injectable } from '@angular/core';
import { Order } from '../../model/order';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class OrderService {

  private orderUrl: string;

  constructor(private http: HttpClient) {
    this.orderUrl =  environment.apiUrl +'/api/order';
  }

  public findAll() {
    return this.http.get<Order[]>(this.orderUrl);
  }

}
