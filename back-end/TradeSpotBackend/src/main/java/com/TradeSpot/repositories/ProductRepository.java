package com.TradeSpot.repositories;

import com.TradeSpot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductByCategoryName(String categoryName);


    @Query("SELECT p FROM Product p WHERE p.user.id != :userId")

    List<Product> findOtherProducts(@Param("userId") long userId);

    @Query(" SELECT p FROM Product p where p.isActive = true and p.user.id != :userId")
    List<Product> listOthersProducts(@Param("userId") long id);

    @Query(" SELECT p FROM Product p where p.isActive = true and p.user.id = :userId")
    List<Product> listUserActiveProducts(@Param("userId") long id);


    @Query(" SELECT COUNT(DISTINCT p.user) from Product p")
    long getSellerCount();

    @Query(" SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> getActiveProducts();
}
