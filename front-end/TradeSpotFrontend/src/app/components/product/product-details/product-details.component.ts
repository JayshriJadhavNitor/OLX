import { Component, Input } from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductListComponent } from '../product-list/product-list.component';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent {

   
   product: Product | undefined;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.productService.findProductById(id).subscribe(product => {
        this.product = product;
      });
    });
  }
}
