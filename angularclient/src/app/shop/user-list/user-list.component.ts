import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../service/user/user-service.service';
import { Router } from '@angular/router';
import { AuthService } from '../../security/auth-service/auth.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[];
  query: string;

  constructor(
    private userService: UserService,
    private router: Router,
    private authService: AuthService
  ) {
    if (!this.authService.hasAdminRole()) {
      this.router.navigate(['/discs']);
    }
  }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.userService.findAll().subscribe(res => {
      this.users = res;
      this.users.sort((o1, o2) => o1.username.toString().localeCompare(o2.username));
    });
  }

  lockUser(id: number) {
    this.userService.delete(id).subscribe(res => this.fetchData());
  }

}
