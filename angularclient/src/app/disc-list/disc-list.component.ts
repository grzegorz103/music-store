import { Component, OnInit } from '@angular/core';
import { Disc } from '../model/disc';
import { DiscService } from '../service/disc.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Component({
  selector: 'app-disc-list',
  templateUrl: './disc-list.component.html',
  styleUrls: ['./disc-list.component.css']
})
export class DiscListComponent implements OnInit {

  discs: Disc[];

  constructor(private discService: DiscService,
    private router: Router) {

  }

  ngOnInit() {
    this.discService.findAll().subscribe(
      data => { this.discs = data; },
      err => this.router.navigate(['/login']));
  }

}
