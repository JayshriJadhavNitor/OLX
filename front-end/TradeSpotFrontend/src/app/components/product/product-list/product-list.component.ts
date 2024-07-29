import { Product } from '../../../models/product';
import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent {
  products: Product[] = [];

  selectedProduct: Product;

  @Input()
  searchText: string = '';

  subscription: Subscription;

  constructor(private searchService: SearchService,private productService:ProductService) {
    this.subscription = this.searchService
      .getSearchText()
      .subscribe((searchText) => {
        this.searchText = searchText;
        // Optionally, trigger search/filter logic based on searchText
      });
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.listAllProducts().subscribe(
      (product) => {
        this.products = product.map((product) => ({
          ...product,
          productImgPath: `${product.productImgPath.substring(
            product.productImgPath.indexOf('/assets') + '/assets'.length
          )}`,
        }));
      },
      (error) => {
        console.error('Error loading products:', error);
      }
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();

    // constructor(private productService: ProductService) { }

    // ngOnInit(): void {
    //   this.loadProducts();
    // }

    // loadProducts(): void {
    //   this.productService.getProducts()
    //     .subscribe(products => {
    //       this.products = products;
    //     });
  }
}
