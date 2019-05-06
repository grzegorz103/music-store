import { Component, OnInit } from '@angular/core';
import { Disc } from '../model/disc';
import { DiscService } from '../service/disc.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { CartService } from '../service/cart-service.service';
import { CartItem } from '../model/cart-item';

@Component({
  selector: 'app-disc-list',
  templateUrl: './disc-list.component.html',
  styleUrls: ['./disc-list.component.css']
})
export class DiscListComponent implements OnInit {

  discs: Disc[];
  list: Array<CartItem> = [];

  constructor(private discService: DiscService,
    private cartService: CartService,
    private router: Router) {
  }

  ngOnInit() {
    this.discService.findAll().subscribe(
      data => {
        this.discs = data;

      },
      err => this.router.navigate(['/login']));

  }

  buyItem(id: number) {
    this.cartService.save(id).subscribe(res => console.log('ok'));
  }

  remove(id: number){
    this.discService.remove(id);
  }

}
