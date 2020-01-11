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
  value = 0;
  ascending: boolean = true;
  previousCriterium: number;
  query: string;

  constructor(private discService: DiscService,
    private cartService: CartService,
    private router: Router,
    public authService: AuthService) {
  }

  ngOnInit() {
    this.fetchData();
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
      for (let i = 0; i < 100; ++i) {
        setTimeout(() => this.addValue(i), i * 15);
      }
      setTimeout(() => this.changeStatus(), 2000);
    });
  }

  addValue(v: number) {
    this.value = v;
  }

  remove(id: number) {
    this.discService.remove(id).subscribe(() => this.fetchData());
  }

  changeStatus() {
    this.bought = false;
    this.value = 0;
  }

  sortList(criterium: number) {
    if (this.previousCriterium !== criterium) {
      this.ascending = true;
    }
    switch (criterium) {
      case 1:
        this.discs.sort((o1, o2) => o1.id.toString().localeCompare(o2.id));
        break;
      case 2:
        this.discs.sort((o1, o2) => o1.band.toString().localeCompare(o2.band));
        break;
      case 3:
        this.discs.sort((o1, o2) => o1.title.toString().localeCompare(o2.title));
        break;
      case 4:
        this.discs.sort((o1, o2) => o1.price - o2.price);
        break;
    }
    if (!this.ascending) {
      this.discs = this.discs.reverse();
    }
    this.ascending = !this.ascending;
    this.previousCriterium = criterium;
  }

}
