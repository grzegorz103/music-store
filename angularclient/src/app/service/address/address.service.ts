import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Address } from '../../model/address';
import { environment } from 'src/environments/environment';

@Injectable()
export class AddressService {

  addressUrl: string;

  constructor(private http: HttpClient) {
    this.addressUrl = environment.apiUrl + '/api/address';
  }

  public getAddress() {
    return this.http.get<Address>(this.addressUrl);
  }

  public update(address: Address){
    return this.http.put<Address>(this.addressUrl, address);
  }
}
