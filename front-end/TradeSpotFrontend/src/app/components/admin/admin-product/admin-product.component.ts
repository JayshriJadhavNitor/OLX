import { Component } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.css']
})
export class AdminProductComponent {

  constructor(private productService: ProductService) { }
  
  displayProduct() {
    this.productService.getProducts().subscribe((result:Product[]) => {
      for (let prod of result) {
        // console.log(prod.);
        
      }
    })
  }
}
