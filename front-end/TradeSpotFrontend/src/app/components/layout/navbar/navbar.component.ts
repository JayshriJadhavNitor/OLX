import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {faCoffee,faUser} from '@fortawesome/free-solid-svg-icons';
import { SearchService } from 'src/app/services/search.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  icon=faUser;

  searchText: string = '';


  constructor(private searchService: SearchService) { }

  updateSearch(event:any): void {
    this.searchText = event.target.value;
    this.searchService.setSearchText(this.searchText);
  }
}
