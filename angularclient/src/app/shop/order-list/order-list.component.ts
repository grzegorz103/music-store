import { Component, OnInit } from '@angular/core';
import { Order } from '../../model/order';
import { Router } from '@angular/router';
import { OrderService } from '../../service/order/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[];

  constructor(private router: Router,
    private orderService: OrderService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.orderService.findAll().subscribe(res => this.orders = res);
  }

  calculateSum(id: number) {
    const self = this;
    let sum = 0;
    const discs = self.orders[id].discs;
    for (let i = 0; i < discs.length; ++i) {
      sum += discs[i].amount * discs[i].disc.price;
    }
    return sum;
  }
}
