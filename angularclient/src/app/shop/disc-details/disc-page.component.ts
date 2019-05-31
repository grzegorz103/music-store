import { Component, OnInit } from '@angular/core';
import { Disc } from '../../model/disc';
import { DiscService } from '../../service/disc/disc.service';
import { CartService } from '../../service/cart/cart-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-disc-page',
  templateUrl: './disc-page.component.html',
  styleUrls: ['./disc-page.component.css']
})
export class DiscPageComponent implements OnInit {

  disc: Disc;
  urlId: number;
  amount = 1;
  infoMsg: string;
  value = 0;

  constructor(private discService: DiscService,
    private cartService: CartService,
    private router: Router,
    private rt: ActivatedRoute) { }

  ngOnInit() {
    this.rt.params.subscribe(params => this.urlId = params['id']);
    this.fetchData();
  }

  fetchData() {
    this.discService.findById(this.urlId).subscribe(res => this.disc = res);
  }

  buy() {
    if (this.amount < 1 || !Number.isInteger(this.amount)) {
      this.infoMsg = 'Please enter correct amount';
      setTimeout(() => this.infoMsg = '', 3000);
      for (let i = 0; i < 100; ++i) {
        setTimeout(() => this.value++, i * 28);
      }
      return;
    }
    this.cartService.save(Number.parseInt(this.disc.id), this.amount)
      .subscribe(res => this.router.navigate(['/cart']));
  }

}
