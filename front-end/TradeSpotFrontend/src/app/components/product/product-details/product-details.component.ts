import { Component, Input } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductListComponent } from '../product-list/product-list.component';
@Component({
  selector: 'product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent {

   
   @Input()
   productListComp: ProductListComponent = undefined;

   product: Product ;

   ngOnInit(){
    this.product = this.productListComp.selectedProduct;
   }

}
