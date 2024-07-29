import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'OLX';

 
  showNavbar = true;
  showProductsContainer: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {

        this.showProductsContainer = event.url === '/' || event.url === '/categories'; 
      }
      if (event instanceof NavigationEnd) {
        if (event.url == '/admin' || event.url=='/admin-product' || event.url=='/addCategory' || event.url=='/adminCategory') {
          this.showNavbar = false;
        }
      }

    });
  }


}
