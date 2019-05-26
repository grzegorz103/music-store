import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../../auth-service/auth.service';

@Injectable()
export class AdminGuardService implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const isLoggedIn = this.authService.isAuthenticated();
    if (isLoggedIn) {
      const hasAdminRole = this.authService.hasAdminRole();
      if (hasAdminRole) {
        return true;
      } else {
        this.router.navigate(['/discs']);
      }
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }
}
