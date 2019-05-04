import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DiscListComponent } from './disc-list/disc-list.component';
import { DiscService } from './service/disc.service';
import { UserFormComponent } from './register-form/register-form.component';
import { UserService } from './service/user-service.service';
import { LoginComponent } from './login/login.component';
import { MainPageComponent} from './main-page/main-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    DiscListComponent,
    UserFormComponent,
    LoginComponent,
    MainPageComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, DiscService],
  bootstrap: [AppComponent]
})
export class AppModule { }