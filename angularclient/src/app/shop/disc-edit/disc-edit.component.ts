import { Component, OnInit } from '@angular/core';
import { DiscService } from '../../service/disc/disc.service';
import { Disc } from '../../model/disc';
import { ActivatedRoute } from '@angular/router';
import { Category } from '../../model/category';
import { CategoryService } from '../../service/category/category.service';

@Component({
  selector: 'app-disc-edit',
  templateUrl: './disc-edit.component.html',
  styleUrls: ['./disc-edit.component.css']
})
export class DiscEditComponent implements OnInit {

  disc: Disc;
  urlId: number;
  info: string;
  isInfo: boolean;
  value: number;
  categories: Category[];

  constructor(
    private discService: DiscService,
    private rt: ActivatedRoute,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.rt.params.subscribe(params => this.urlId = params['id']);
    this.categoryService.findAll().subscribe(res => this.categories = res);
    this.discService.findById(this.urlId).subscribe(res => this.disc = res);
  }

  update() {
    this.discService.update(this.urlId, this.disc)
      .subscribe(res => {
        this.disc = res;
        this.info = 'Disc has been updated successfully!';
        this.isInfo = true;
      }, err => {
        this.info = 'Failed to update the disc';
        this.isInfo = true;
      });
    for (let i = 0; i < 100; ++i) {
      setTimeout(() => this.addValue(i), i * 15);
    }
    setTimeout(() => this.isInfo = false, 2000);
  }

  addValue(val: number) {
    this.value = val;
  }

}
