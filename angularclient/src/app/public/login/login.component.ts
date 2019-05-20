import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  model: any = {};
  invalidText = 'Incorrect login and/or password';
  isInvalid: boolean;
  info: boolean;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) { }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }
  login() {
    this.http.post<Observable<boolean>>('http://localhost:8080/api/users/login', {
      username: this.model.username,
      password: this.model.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.model.username + ':' + this.model.password)
        );
        this.info = true;
        setTimeout(() => {
          this.info = false;
          this.router.navigate(['/discs']);
        }, 5000);

      } else {
        this.isInvalid = true;
        setTimeout(() => this.isInvalid = false, 3000);
      }
    });
  }
}
