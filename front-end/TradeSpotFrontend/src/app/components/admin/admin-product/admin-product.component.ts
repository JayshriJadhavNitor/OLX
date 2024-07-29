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
  products = [];
  
  ngOnInit() {
    this.displayProduct();
  }
  
  displayProduct() {
    this.productService.listAllProducts().subscribe((result: Product[]) => {
      if (result) {
        console.log(result);
          this.products=result
      }
    })
  }
}
