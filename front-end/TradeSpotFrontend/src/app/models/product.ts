// src/app/models/product.ts
export interface Product {
    id: number;
    title: string;
    description: string;
    price: number;
    image_path?: string; // Optional property for product images
  }
  