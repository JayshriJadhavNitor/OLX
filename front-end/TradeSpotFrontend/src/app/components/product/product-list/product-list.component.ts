import { Product } from '../../../models/product';
import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ProductService } from 'src/app/services/product.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  products:Product[] = [];
  
  
  @Input()
  searchText: string = '';

  subscription: Subscription;

  // userId: number = 0;


  constructor(private searchService: SearchService, private productService: ProductService  ) {
   
    this.subscription = this.searchService.getSearchText().subscribe(searchText => {
      this.searchText = searchText;
      
      
    },

    
   
  );
    
  }

 


  ngOnInit(): void {
    // this.userId = JSON.parse(sessionStorage.getItem("user")) && JSON.parse(sessionStorage.getItem("user")).id;
    
    this.loadProducts();
    
  }

  // loadProductsBasedOnState(): void {
  //   if (this.userId) {
  //     this.loadUserBasedProducts(this.userId);
     
  //   } else {
  //     this.loadProducts();
  //   }
  // }

  loadProducts(): void {
    this.productService.getActiveProducts().subscribe(
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
  }

  // loadUserBasedProducts(userId:number): void{
  //   this.productService.getProducts(userId).subscribe(
  //     (product) => {
  //       this.products = product.map(product => ({
  //         ...product,
  //         productImgPath: `${product.productImgPath.substring(product.productImgPath.indexOf('/assets') + '/assets'.length)}`
  //       }));
  //     },
  //     (error) => {
  //       console.error('Error loading products:', error);
  //     }
  //   )
  // }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();

  // constructor(private productService: ProductService) { }


  

}
}