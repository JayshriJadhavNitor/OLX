package com.TradeSpot.repositories;

import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.SellItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellItemsRepo extends JpaRepository<SellItems, Long> {

    @Query("SELECT COUNT(DISTINCT p.seller) FROM SellItems p")
    long findSellerCount();

    @Query("SELECT bi.sellproduct FROM SellItems bi WHERE bi.seller.id = :buyerId")
    List<Product> findSellProduct(@Param("buyerId") long id);

    @Query("SELECT bi.sellproduct FROM SellItems bi WHERE bi.seller.id != :buyerId")
    List<Product> findProduct(@Param("buyerId") long id);

    @Query("SELECT bi.sellproduct FROM SellItems bi WHERE bi.seller.id = :buyerId")
    List<Product> findProductsByUserId(@Param("buyerId") long id);

//    @Query("SELECT si.product FROM SellItems si WHERE si.buyer.id = :userId")
//    List<Product> getsellingProduct(@Param("userId") Long userId);

}
