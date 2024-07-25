import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import {faCoffee} from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'OLX';

  faCoffee=faCoffee;
  showProductsContainer: boolean = true;

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Check if the current route should hide <products-container>
        this.showProductsContainer = event.url === '/' || event.url === '/categories'; // Adjust based on your routes
      }
    });
  }


}
