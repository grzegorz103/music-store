import { Component, OnInit } from '@angular/core';
import { CartService } from '../../service/cart/cart-service.service';
import { CartItem } from '../../model/cart-item';
import { CartDto } from '../../model/cart-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  items: CartDto;
  sum: number;
  isError: boolean;
  error: string;
  showRedirect: boolean;

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
    this.cartService.buy().subscribe(res => {
      this.router.navigate(['/orders']);
    },
      err => {
        const code: number = err.status;
        if (code === 400) {
          this.error = 'Cart is empty!';
        } else if (code === 403) {
          this.error = 'User details is empty! ';
          this.showRedirect = true;
        } else if (code === 412) {
          this.error = 'Following products are not available:\r\n' + err.error;
        }
        this.isError = true;
        setTimeout(() => {
          this.isError = false;
          this.showRedirect = false;
        }, 5000);
      });
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
