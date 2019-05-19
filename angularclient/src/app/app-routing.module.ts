import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiscListComponent } from './shop/disc-list/disc-list.component';
import { UserFormComponent } from './public/register-form/register-form.component';
import { LoginComponent } from './public/login/login.component';
import { MainPageComponent } from './public/main-page/main-page.component';
import { DiscFormComponent } from './shop/disc-form/disc-form.component';
import { CartComponent } from './shop/cart/cart.component';
import { OrderListComponent } from './shop/order-list/order-list.component';
import { DiscPageComponent } from './shop/disc-page/disc-page.component';
import { AddressComponent } from './shop/address/address.component';
import { AuthGuardService } from './security/guard/auth-guard.service';

const routes: Routes = [
  { path: 'discs', component: DiscListComponent, data: { animation: 'Disc' }, canActivate: [AuthGuardService] },
  { path: 'register', component: UserFormComponent, data: { animation: 'Register' } },
  { path: 'login', component: LoginComponent, data: { animation: 'Login' } },
  { path: 'main', component: MainPageComponent, data: { animation: 'Main' } },
  { path: 'discs/add', component: DiscFormComponent, data: { animation: 'Add' }, canActivate: [AuthGuardService] },
  { path: 'cart', component: CartComponent, data: { animation: 'Cart' }, canActivate: [AuthGuardService] },
  { path: 'orders', component: OrderListComponent, data: { animation: 'Orders' }, canActivate: [AuthGuardService] },
  { path: 'disc/:id', component: DiscPageComponent, data: { animation: 'Details' }, canActivate: [AuthGuardService] },
  { path: 'profile', component: AddressComponent, data: { animation: 'Profile' }, canActivate: [AuthGuardService] },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: [],
  providers: [
    AuthGuardService
  ]
})
export class AppRoutingModule { }
