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

const routes: Routes = [
  { path: 'discs', component: DiscListComponent },
  { path: 'register', component: UserFormComponent },
  { path: 'login', component: LoginComponent },
  { path: 'main', component: MainPageComponent },
  { path: 'discs/add', component: DiscFormComponent },
  { path: 'cart', component: CartComponent },
  { path: 'orders', component: OrderListComponent },
  { path: 'disc/:id', component: DiscPageComponent},
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
