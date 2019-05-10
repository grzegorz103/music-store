import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DiscListComponent } from './disc-list/disc-list.component';
import { DiscService } from './service/disc.service';
import { UserFormComponent } from './register-form/register-form.component';
import { UserService } from './service/user-service.service';
import { LoginComponent } from './login/login.component';
import { MainPageComponent } from './main-page/main-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { DiscFormComponent } from './disc-form/disc-form.component';
import { CartComponent } from './cart/cart.component';
import { CartService } from './service/cart-service.service';
import { OrderListComponent } from './order-list/order-list.component';
import { OrderService } from './service/order.service';
import { RequestInterceptor } from './auth/request-interceptor';
import { AuthService } from './auth/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    DiscListComponent,
    UserFormComponent,
    LoginComponent,
    MainPageComponent,
    NavbarComponent,
    FooterComponent,
    DiscFormComponent,
    CartComponent,
    OrderListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, DiscService, CartService, OrderService, AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }