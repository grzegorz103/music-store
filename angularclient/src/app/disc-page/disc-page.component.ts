import { Component, OnInit } from '@angular/core';
import { Disc } from '../model/disc';
import { DiscService } from '../service/disc.service';
import { CartService } from '../service/cart-service.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-disc-page',
  templateUrl: './disc-page.component.html',
  styleUrls: ['./disc-page.component.css']
})
export class DiscPageComponent implements OnInit {

  disc: Disc;
  urlId: number;

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

}
