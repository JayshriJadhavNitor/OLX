package com.TradeSpot.repositories;

import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.SellItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellItemsRepo extends JpaRepository<SellItems, Long> {

    List<Product> getProductBySellerId( Long sellerId);

}
