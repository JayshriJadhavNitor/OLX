package com.TradeSpot.repositories;

import com.TradeSpot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {


}
