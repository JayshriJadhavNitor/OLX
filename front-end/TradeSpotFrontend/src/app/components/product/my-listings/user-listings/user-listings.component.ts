import { Component, Input } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'user-listings',
  templateUrl: './user-listings.component.html',
  styleUrls: ['./user-listings.component.css']
})
export class UserListingsComponent {

  products:Product[] = [];


  subscription?: Subscription;

  constructor(private productService: ProductService){
   
    this.userId = JSON.parse(sessionStorage.getItem("user")) && JSON.parse(sessionStorage.getItem("user")).id;
  }

  userId: number = 0;


  ngOnInit(): void {
    this.loadProductsBasedUserId(this.userId);
    
  }

  loadProductsBasedUserId(userId:number): void{
    this.productService.getProductsByUserId(userId).subscribe(
      (product) => {
        this.products = product.map(product => ({
          ...product,
          productImgPath: `${product.productImgPath.substring(product.productImgPath.indexOf('/assets') + '/assets'.length)}`
        }));
      },
      (error) => {
        console.error('Error loading products:', error);
      }
    )
  }


  onRemoveProduct(product: Product): void {
    
      this.productService.deleteProduct(product.id).subscribe(
        () => {
          this.products = this.products.filter(p => p.id !== product.id);
        },
        (error) => {
          console.error('Error deleting product:', error);
        }
      );
    
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();


}

}
