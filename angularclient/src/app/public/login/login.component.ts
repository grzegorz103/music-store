import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../security/auth-service/auth.service';
import { NavbarComponent } from '../../shared/navbar/navbar.component';
import { environment } from 'src/environments/environment';

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
  timer: any;
  value: number;
  loading = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private authService: AuthService
  ) {
  }

  ngOnInit() {
    sessionStorage.setItem('token', '');
  }

  login() {
    this.http.post<Observable<boolean>>(environment.apiUrl + '/api/users/login', {
      username: this.model.username,
      password: this.model.password
    }).subscribe(isValid => {
      if (isValid) {
        sessionStorage.setItem(
          'token',
          btoa(this.model.username + ':' + this.model.password)
        );
        this.info = true;
        this.authService.fetchAdminRole();

        for (let i = 0; i < 100; ++i) {
          setTimeout(() => this.addValue(i), i * 50);
        }
        this.timer = setTimeout(() => {
          this.info = false;
          this.router.navigate(['/discs']);
        }, 5000);

      } else {
        this.isInvalid = true;
        setTimeout(() => this.isInvalid = false, 3000);
      }
    });
  }

  addValue(v: number) {
    this.value = v;
  }

  redirect() {
    this.router.navigate(['/discs']);
    this.info = false;
    clearTimeout(this.timer);
  }
}
