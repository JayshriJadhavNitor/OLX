import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavbarComponent } from './components/layout/navbar/navbar.component';
import { LoginComponent } from './components/registration/login/login.component';
import { ForgotPasswordComponent } from './components/registration/forgot-password/forgot-password.component';
import { RegisterComponent } from './components/registration/register/register.component';
import { CategoriesComponent } from './components/landing-page/categories/categories.component';
import { AboutusComponent } from './components/layout/footer/aboutus/aboutus.component';
import { FaqComponent } from './components/layout/footer/faq/faq.component';
import { FeaturesComponent } from './components/layout/footer/features/features.component';
import { AdminDashboardComponent } from './components/admin/admin-dashboard/admin-dashboard.component';
import { UserDashComponent } from './components/user/user-dash/user-dash.component';
import { ContactComponent } from './components/layout/footer/contact/contact.component';
import { ProductsContainerComponent } from './components/product/products-container/products-container.component';
import { NewProductComponent } from './components/product/new-product/new-product.component';
import { AdminProductComponent } from './components/admin/admin-product/admin-product.component';
import { NewCategoryComponent } from './components/admin/new-category/new-category.component';
import { CategoryListComponent } from './components/admin/category-list/category-list.component';
import { authGuard } from './auth.guard';

const routes: Routes = [
      { path: '', redirectTo: '/categories', pathMatch: 'full' },
      { path: 'categories', component: CategoriesComponent },
      { path: 'login', component: LoginComponent },
      { path: 'forgotpassword', component: ForgotPasswordComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'aboutus', component: AboutusComponent },
      { path: 'faq', component: FaqComponent },
      { path: 'features', component: FeaturesComponent },
      { path: 'admin', component: AdminDashboardComponent,canActivate:[authGuard] }, // Admin Dashboard
      { path: 'user', component: UserDashComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'products', component: ProductsContainerComponent },
      { path: 'addProduct', component: NewProductComponent,canActivate:[authGuard] },
      { path: 'admin-product', component: AdminProductComponent,canActivate:[authGuard] },
      {path:'adminCategory',component:CategoryListComponent,canActivate:[authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
