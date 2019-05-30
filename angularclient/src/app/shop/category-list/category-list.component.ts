import { Component, OnInit } from '@angular/core';
import { Category } from '../../model/category';
import { CategoryService } from '../../service/category/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  categories: Category[];
  category: Category;

  constructor(
    private categoryService: CategoryService
  ) { }

  ngOnInit() {
    this.fetchData();
    this.category = new Category();
  }

  fetchData() {
    this.categoryService.findAll().subscribe(
      res => this.categories = res.sort((o1, o2) => o1.name.toString().localeCompare(o2.name))
    );
  }

  save() {
    this.categoryService.create(this.category).subscribe(res => this.fetchData());
  }

  remove(id: number) {
    this.categoryService.delete(id).subscribe(res => this.fetchData());
  }

  update(id: number) {
    this.categoryService.update(id, this.categories.find(e => e.id === id))
      .subscribe(res => this.fetchData());
  }

}
