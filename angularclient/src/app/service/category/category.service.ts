import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../../model/category';

@Injectable()
export class CategoryService {

  private categoryUrl: string;

  constructor(private http: HttpClient) {
    this.categoryUrl = 'http://localhost:8080/api/category/';
  }

  public findAll() {
    return this.http.get<Category[]>(this.categoryUrl);
  }

  public create(category: Category) {
    return this.http.post<Category>(this.categoryUrl, category);
  }

}
