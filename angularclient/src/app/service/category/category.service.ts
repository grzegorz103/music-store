import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Category } from '../../model/category';
import { environment } from 'src/environments/environment';

@Injectable()
export class CategoryService {

  private categoryUrl: string;

  constructor(private http: HttpClient) {
    this.categoryUrl = environment.apiUrl + '/api/category/';
  }

  public findAll() {
    return this.http.get<Category[]>(this.categoryUrl);
  }

  public create(category: Category) {
    return this.http.post<Category>(this.categoryUrl, category);
  }

  public update(id: number, category: Category){
    return this.http.put<Category>(this.categoryUrl + id, category);
  }

  public delete(id: number) {
    return this.http.delete(this.categoryUrl + id);
  }

}
