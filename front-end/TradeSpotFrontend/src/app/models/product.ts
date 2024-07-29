// src/app/models/product.ts
export interface Product {
  id: number;
  productName: string;
  description: string;
  price: number;
  addedDate: Date;
  active: boolean;
  productImgPath?: string;
}
  