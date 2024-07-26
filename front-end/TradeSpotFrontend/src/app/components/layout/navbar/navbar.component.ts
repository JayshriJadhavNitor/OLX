import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {faUser} from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { SearchService } from 'src/app/services/search.service';
import { SessionService } from 'src/app/services/session.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  icon=faUser;

  searchText: string = '';

  private userSubscription: Subscription = new Subscription();
  isUser: boolean;
  loggedIn: boolean;
  route: string;

  constructor(private searchService: SearchService, private router:Router,private sessionService:SessionService) { }

  ngOnInit(): void {
    this.checkUserRole();
  }

  checkUserRole(): void {
    //const userRole = this.sessionService.getUserRole();

    this.userSubscription = this.sessionService.userRole$.subscribe((userRole)=>{
      if (userRole === 'USER') {
        this.isUser = true;
        this.loggedIn = true;
        this.route = 'user-home';
      }
    })
  
  }

  logOut(): void {
    this.sessionService.removeSessionItems();
    this.isUser = false;
    this.loggedIn = false;
  }

  

  updateSearch(event:any): void {
    this.searchText = event.target.value;
    this.searchService.setSearchText(this.searchText);
  }

  ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }
  

}
