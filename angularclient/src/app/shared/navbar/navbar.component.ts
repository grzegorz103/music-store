import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../security/auth-service/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  constructor(private router: Router,
    private authService: AuthService) { }

  ngOnInit() {
  
  }

  verifyRoles() {
    this.authService.hasAdminRole();
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  logout() {
    sessionStorage.clear();
    this.authService.adminRole = false;
    this.router.navigate(['/login']);
  }

}
