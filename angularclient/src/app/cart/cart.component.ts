import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart-service.service';
import { CartItem } from '../model/cart-item';
import { CartDto } from '../model/cart-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  items: CartDto;
  sum: number;

  constructor(private router: Router,
    private cartService: CartService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.cartService.findAll().subscribe(
      res => {
        this.items = res;
        this.calculateSum();
      });
  }

  remove(id: number) {
    this.cartService.remove(id).subscribe(() => this.fetchData());
  }

  buy() {
    this.cartService.buy().subscribe(res => this.router.navigate(['/orders']));
  }

  calculateSum() {
    var self = this;
    let total = 0;
    for (let i = 0; i < self.items.list.length; ++i) {
      total += self.items.list[i].amount * self.items.list[i].disc.price;
    }
    this.sum = total;
  }
}
