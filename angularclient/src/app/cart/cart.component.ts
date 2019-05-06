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

  constructor(private router: Router,
    private cartService: CartService) { }

  ngOnInit() {
    this.cartService.findAll()
    .subscribe(res => this.items = res);
  }
}
