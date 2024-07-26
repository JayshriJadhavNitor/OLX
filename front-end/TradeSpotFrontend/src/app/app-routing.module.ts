import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/registration/login/login.component';
import { ForgotPasswordComponent } from './components/registration/forgot-password/forgot-password.component';
import { RegisterComponent } from './components/registration/register/register.component';
import { CategoriesComponent } from './components/landing-page/categories/categories.component';
import { AboutusComponent } from './components/layout/footer/aboutus/aboutus.component';
import { FaqComponent } from './components/layout/footer/faq/faq.component';
import { FeaturesComponent } from './components/layout/footer/features/features.component';
import { ContactComponent } from './components/layout/footer/contact/contact.component';
import { ProductsContainerComponent } from './components/product/products-container/products-container.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: '', redirectTo: '/categories', pathMatch: 'full' },
      { path: 'categories', component: CategoriesComponent },
      { path: 'login', component: LoginComponent },
      { path: 'forgotpassword', component: ForgotPasswordComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'aboutus', component: AboutusComponent },
      { path: 'faq', component: FaqComponent },
      { path: 'features', component: FeaturesComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'products', component: ProductsContainerComponent}

    ]
  }
  // {
  //   path: '',
  //   component: AdminLayoutComponent,
  //   children: [
  //     { path: 'admin-dash', component: AdminDashboardComponent }
  //   ]
  // },
  // {
  //   path: '',
  //   component: UserDashComponent,
  //   children: [
  //     { path: 'user-dash', component: UserDashComponent }
  //   ]
  // }

  // { path: '**', component: NavbarComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
