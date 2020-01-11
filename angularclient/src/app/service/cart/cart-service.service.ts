import { Injectable } from '@angular/core';
import { CartItem } from '../../model/cart-item';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { CartDto } from '../../model/cart-dto';
import { environment } from 'src/environments/environment';


@Injectable()
export class CartService {

  private cartUrl: string;

  constructor(private http: HttpClient) {
    this.cartUrl =  environment.apiUrl + '/api/cart';
  }

  public findAll() {
    return this.http.get<CartDto>(this.cartUrl);
  }

  public save(id: number, amount: number) {
    return this.http.post(this.cartUrl + '/' + id + "?amount=" + amount, null);
  }

  public remove(id: number) {
    return this.http.delete(this.cartUrl + '/' + id);
  }

  public buy() {
    return this.http.put(this.cartUrl, null);
  }
}
