import { Injectable } from '@angular/core';
import { CartItem } from '../model/cart-item';
import { HttpClient, HttpParams } from '@angular/common/http';


@Injectable()
export class CartService {

  private cartUrl: string;

  constructor(private http: HttpClient) {
    this.cartUrl = 'http://localhost:8080/api/cart';
  }

  public findAll() {
    console.log('22222');
    return this.http.get<CartItem[]>(this.cartUrl);
  }

  public save(id: number) {
    const params = new URLSearchParams();
    params.set('id', id.toString());
    return this.http.post(this.cartUrl, params);
  }
}
