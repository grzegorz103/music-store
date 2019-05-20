import { Component, OnInit } from '@angular/core';
import { Disc } from '../../model/disc';
import { DiscService } from '../../service/disc/disc.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { CartService } from '../../service/cart/cart-service.service';
import { CartItem } from '../../model/cart-item';
import { AuthService } from '../../security/auth-service/auth.service';

@Component({
  selector: 'app-disc-list',
  templateUrl: './disc-list.component.html',
  styleUrls: ['./disc-list.component.css']
})
export class DiscListComponent implements OnInit {

  discs: Disc[];
  list: Array<CartItem> = [];
  bought: boolean;
  text: string = 'Product has been added to your shopping cart';
  adminRole: boolean;

  constructor(private discService: DiscService,
    private cartService: CartService,
    private router: Router,
    private authService: AuthService) {
  }

  ngOnInit() {
    this.fetchData();
    this.verifyRole();
  }

  fetchData() {
    this.discService.findAll().subscribe(
      data => {
        this.discs = data;
      }, err => this.router.navigate(['/login']));
  }

  buyItem(id: number, amount: number) {
    this.cartService.save(id, amount).subscribe(res => {
      this.bought = true;
      setTimeout(() => this.changeStatus(), 2000)
    });
  }

  remove(id: number) {
    this.discService.remove(id).subscribe(() => this.fetchData());
  }

  changeStatus() {
    this.bought = false;
  }

  verifyRole() {
    this.authService.hasAdminRole().subscribe(res => {
      this.adminRole = res;
    });
  }

}
