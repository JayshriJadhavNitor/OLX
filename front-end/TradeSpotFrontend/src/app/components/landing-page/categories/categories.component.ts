// categories.component.ts
import { Component, OnInit } from '@angular/core';

import { Category } from 'src/app/models/category';
import { Product } from 'src/app/models/product';
import { CategoryService } from 'src/app/services/category.service';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  categories: Category[] = [];
  products: Product[] = [];
  currentCategory: string;

  toggle: boolean = true

  constructor(private categoryService: CategoryService , private productService: ProductService) {
    
   }

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
    this.currentCategory = categoryName;
     this.productService.getProductsByCategory(categoryName).subscribe(
      (product) => {
        this.products = product.map(product => ({
          ...product,
          productImgPath: `${product.productImgPath.substring(product.productImgPath.indexOf('/assets') + '/assets'.length)}`
        }));
      },
      (error) => {
        console.error('Error loading products:', error);
      }
    );

    this.toggle = false;
  }
}