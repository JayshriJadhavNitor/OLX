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
export class ProductListComponent {


  products:Product[] = [{
    "id": 1,
    "title": "Laptop",
    "description": "Powerful laptop for all your computing needs",
    "price": 59999.00,
    "image_path": "https://media.wired.com/photos/64daad6b4a854832b16fd3bc/4:3/w_1787,h_1340,c_limit/How-to-Choose-a-Laptop-August-2023-Gear.jpg"
  },
  {
    "id": 2,
    "title": "Smartphone",
    "description": "Feature-packed smartphone with high-resolution display",
    "price": 39000.00,
    "image_path": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1oP13dClTSe1pO-QEbvA_ICdR027-X9e2hw&s"
  },

  {
    "id": 3,
    "title": "Headphones",
    "description": "Noise-cancelling headphones for immersive audio experience",
    "price": 2000.00,
    "image_path": "https://images.pexels.com/photos/205926/pexels-photo-205926.jpeg?cs=srgb&dl=pexels-kinkate-205926.jpg&fm=jpg"
  },
  {
    "id": 4,
    "title": "Bed",
    "description": "Comfortable Queen size wooden bed",
    "price": 7999.00,
    "image_path": "https://m.media-amazon.com/images/I/713Sm54aAgL.jpg"
  },
  {
    "id": 5,
    "title": "Harrier SUV",
    "description": "SUV in almost new condition",
    "price": 1900000.00,
    "image_path": "https://lh3.googleusercontent.com/proxy/Gf78CJPRv1smMfvOoPj2tuMpUXRidhIORlRtLehYJRB5SiLZjXsYaXM-IJESImwtjxkuWnS_lkl6p1DVmD_Uup9Cjl7xzd_10IgWOQW8Qa26zoZvBUDCSDunlYXF-KPpC7JV_7ahlEMCMsUj7Pxphbw"
  }];
  
  selectedProduct:Product;
  
  @Input()
  searchText: string = '';

  subscription: Subscription;

  constructor(private searchService: SearchService) {
    this.subscription = this.searchService.getSearchText().subscribe(searchText => {
      this.searchText = searchText;
      // Optionally, trigger search/filter logic based on searchText
    });
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
