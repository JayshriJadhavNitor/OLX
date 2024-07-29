// src/app/services/product.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/models/product';
import { ProductDTO } from '../models/productDTO';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  
  private baseUrl = 'http://localhost:8080/product';

  constructor(private http: HttpClient) { }

  addProduct(categoryName: string, userId: number, productDTO: ProductDTO, file: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('categoryName', categoryName);
    formData.append('image', file, file.name);
    formData.append('productName', productDTO.productName);
   
    formData.append('description', productDTO.description);
 
    formData.append('addedDate', productDTO.addedDate);
    formData.append('price', productDTO.price.toString());
   
 
    return this.http.post<any>(`${this.baseUrl}/${userId}`, formData);
  }
 
  listAllProducts(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}`)
  }
 
  findProductById(productId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${productId}`)
  }
 
  buyProduct(userId: number, productId: number): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/buyproduct/${userId}/${productId}`, {})
  }
 
  deleteProduct(productId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${productId}`);
  }
 
}
