import { Injectable } from '@angular/core';
import { CartItem } from '../model/cart-item';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CartDto } from '../model/cart-dto';


@Injectable()
export class CartService {

  private cartUrl: string;

  constructor(private http: HttpClient) {
    this.cartUrl = 'http://localhost:8080/api/cart';
  }

  public findAll() {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    let options = { headers: headers };
    return this.http.get<CartDto>(this.cartUrl, options);
  }

  public save(id: number) {
    let headers: HttpHeaders = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    let options = { headers: headers };
    const params = new URLSearchParams();
    params.set('id', id.toString());
    return this.http.post(this.cartUrl, params, options);
  }
}
