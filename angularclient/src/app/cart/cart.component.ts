import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart-service.service';
import { CartItem } from '../model/cart-item';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  
  items: CartItem[];

  constructor(private router: Router,
    private cartService: CartService) { }

  ngOnInit() {
    this.cartService.findAll().subscribe(
      data => { this.items = data; },
      err => this.router.navigate(['/login']));
  }
}
