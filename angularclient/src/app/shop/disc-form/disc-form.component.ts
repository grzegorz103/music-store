import { Component, OnInit } from '@angular/core';
import { DiscService } from '../../service/disc/disc.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Disc } from '../../model/disc';
import { CategoryService } from '../../service/category/category.service';
import { Category } from '../../model/category';

@Component({
  selector: 'app-disc-form',
  templateUrl: './disc-form.component.html',
  styleUrls: ['./disc-form.component.css']
})
export class DiscFormComponent implements OnInit {

  disc: Disc;
  categories: Category[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private discService: DiscService,
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.disc = new Disc();
    this.disc.category = new Category();
    this.disc.images = [];
    this.fetchCategories();
  }

  onSubmit() {
    this.discService.save(this.disc)
      .subscribe(result => this.goToDiscList(), res => alert('Failed to add new disc'));
  }

  goToDiscList() {
    this.router.navigate(['/discs']);
  }

  fetchCategories() {
    this.categoryService.findAll().subscribe(res => this.categories = res);
  }

}
