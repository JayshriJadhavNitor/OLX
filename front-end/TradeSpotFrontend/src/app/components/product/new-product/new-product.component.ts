import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductDTO } from 'src/app/models/productDTO';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css'],
})
export class NewProductComponent {
  addProductForm: FormGroup;
  selectedFile: File;

  constructor(private fb: FormBuilder, private productService: ProductService) {
    {
      this.addProductForm = this.fb.group({
        productName: ['', Validators.required],
        description: ['', Validators.required],
        price: [0, [Validators.required, Validators.min(0)]], // Ensure price is a non-negative number
        addedDate: [
          new Date().toISOString().split('T')[0],
          Validators.required,
        ], // Initialize with today's date in YYYY-MM-DD format
        categoryName: ['', Validators.required],
        image: [null, Validators.required], // File input for image
      });
    }
  }

  onSubmit(): void {
    const categoryName = this.addProductForm.value.categoryName;
    console.log(categoryName);

    const userId: number = JSON.parse(sessionStorage.getItem('user')).id;
    const productDTO: ProductDTO = {
      productName: this.addProductForm.value.productName,
      description: this.addProductForm.value.description,
      price: this.addProductForm.value.price,
      addedDate: this.addProductForm.value.addedDate,
    };

    this.productService
      .addProduct(categoryName, userId, productDTO, this.selectedFile)
      .subscribe(
        (response: any) => {
          console.log(response.message); // Handle success message
        },
        (error) => {
          console.error('Error adding product:', error); // Handle error
        }
      );
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }
}
