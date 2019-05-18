import { Component, OnInit } from '@angular/core';
import { AddressService } from '../../service/address/address.service';
import { Address } from '../../model/address';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  address: Address;
  success: string;
  isDone: boolean;

  constructor(private addressService: AddressService) {
    this.success = 'Your profile has been updated!';
  }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.addressService.getAddress().subscribe(
      res => this.address = res
    );
  }

  updateAddress() {
    this.addressService.update(this.address).subscribe(res => {
      this.address = res;
      this.isDone = true;
      setTimeout(() => this.isDone = false, 5000);
    }
    );
  }

}
