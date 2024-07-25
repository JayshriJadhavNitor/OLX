// categories.component.ts
import { Component, OnInit } from '@angular/core';

import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  categories: Category[] = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(
      (categories) => {
        this.categories = categories.map(category => ({
          ...category,
          categoryImgPath: `${category.categoryImgPath.substring(category.categoryImgPath.indexOf('/assets') + '/assets'.length)}`
        })); 
      },
      (error) => {
        console.error('Error fetching categories: ', error);
      }
    );
  }

  loadProductsByCategory(categoryName: string): void {
    // Implement logic to load products by category
  }

  onSearch(): void {
    // Implement search functionality if needed
  }
}
