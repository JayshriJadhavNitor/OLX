import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router'; // Import RouterModule and Routes
import { AppComponent } from './app.component';
import { ProductService } from './services/product.service';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { LoginComponent } from './components/registration/login/login.component';
import { ForgotPasswordComponent } from './components/registration/forgot-password/forgot-password.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome'; 
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './components/registration/register/register.component';
import { CategoriesComponent } from './components/landing-page/categories/categories.component';
import { FooterComponent } from './components/layout/footer/footer.component';
import { AboutusComponent } from './components/layout/footer/aboutus/aboutus.component';
import { FaqComponent } from './components/layout/footer/faq/faq.component';
import { FeaturesComponent } from './components/layout/footer/features/features.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { UserDashComponent } from './components/user/user-dash/user-dash.component';
import { ContactComponent } from './components/layout/footer/contact/contact.component';
import { AdminLayoutComponent } from './components/admin/admin-layout/admin-layout.component';
import { ProductComponent } from './components/product/product.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductsContainerComponent } from './components/product/products-container/products-container.component';



// Define your routes here
const routes: Routes = [
  { path: '', component: CategoriesComponent },
  // Add other routes here
];

@NgModule({
  declarations: [
    AppComponent,
    CategoriesComponent,
    NavbarComponent,
    LoginComponent,
    ForgotPasswordComponent,
    RegisterComponent,
    FooterComponent,
    AboutusComponent,
    FaqComponent,
    FeaturesComponent,
    AdminDashboardComponent,
    UserDashComponent,
    ContactComponent,
    AdminLayoutComponent,
    ProductListComponent,
    ProductComponent,
    ProductsContainerComponent,
    ProductDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    FontAwesomeModule, // Import RouterModule and configure routes
    ReactiveFormsModule
  ],
  providers: [ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { 

  

}
