import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user';
import { UserService } from '../../service/user/user-service.service';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  user: User;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.userService.findCurrentUser().subscribe(res => {
    this.user = res;
    this.user.password = null;
    });
  }

  updateUser() {
    this.userService.update(this.user.id, this.user).subscribe(res => {
      sessionStorage.setItem(
        'token',
        btoa(this.user.username + ':' + this.user.password)
      );
      this.fetchData();
    });
  }

}
