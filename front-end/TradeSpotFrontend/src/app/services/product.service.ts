import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ProductDTO } from '../models/productDTO';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/product'; // Replace with your backend base URL

  constructor(private http: HttpClient) {}

  addProduct(
    categoryName: string,
    userId: number,
    productDTO: ProductDTO,
    file: File
  ): Observable<any> {
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
    return this.http
      .get<Product[]>(`${this.baseUrl}`)
      .pipe(catchError(this.handleError));
  }

  getProducts(userId: number): Observable<any[]> {
    return this.http
      .get<any[]>(`${this.baseUrl}/getproducts/${userId}`)
      .pipe(catchError(this.handleError));
  }

  getProductsByUserId(userId: number): Observable<any[]> {
    return this.http
      .get<any[]>(`${this.baseUrl}/getproductsbyuserId/${userId}`)
      .pipe(catchError(this.handleError));
  }

  findProductById(productId: number): Observable<any> {
    return this.http
      .get<any>(`${this.baseUrl}/${productId}`)
      .pipe(catchError(this.handleError));
  }

  buyProduct(userId: number, productId: number): Observable<any> {
    return this.http
      .post<any>(`${this.baseUrl}/buyproduct/${userId}/${productId}`, {})
      .pipe(catchError(this.handleError));
  }

  deleteProduct(productId: number): Observable<any> {
    return this.http
      .delete<any>(`${this.baseUrl}/${productId}`)
      .pipe(catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // Return an observable with a user-facing error message.
    return throwError('Something bad happened; please try again later.');
  }
}
