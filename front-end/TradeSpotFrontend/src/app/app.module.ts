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
import { ContactComponent } from './components/layout/footer/contact/contact.component';
import { ProductComponent } from './components/product/product.component';
import { ProductDetailsComponent } from './components/product/product-details/product-details.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { ProductsContainerComponent } from './components/product/products-container/products-container.component';
import { AdminProductComponent } from './components/admin/admin-product/admin-product.component';
import { MyListingsComponent } from './components/product/my-listings/my-listings.component';
import { NewProductComponent } from './components/product/new-product/new-product.component';
import { AdminNavbarComponent } from './components/admin/admin-navbar/admin-navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialogModule } from '@angular/material/dialog';
import { MatListModule } from '@angular/material/list';
import { NewCategoryComponent } from './components/admin/new-category/new-category.component';
import { CategoryListComponent } from './components/admin/category-list/category-list.component';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { UserListingsComponent } from './components/product/my-listings/user-listings/user-listings.component';
import { UserProductCardComponent } from './components/product/my-listings/user-listings/user-product-card/user-product-card.component';


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
    ContactComponent,
    ProductListComponent,
    ProductComponent,
    ProductsContainerComponent,
    ProductDetailsComponent,
    AdminProductComponent,
    AdminDashboardComponent,
    MyListingsComponent,
    NewProductComponent,
    MyListingsComponent,
    AdminNavbarComponent,
    NewCategoryComponent,
    CategoryListComponent,
    UserListingsComponent,
    UserProductCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatDividerModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatDialogModule,
  ],
  providers: [ProductService],
  bootstrap: [AppComponent],
})
export class AppModule {}
