import { Component, OnInit } from '@angular/core';
import { Order } from '../model/order';
import { Router } from '@angular/router';
import { OrderService } from '../service/order.service';

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
}
