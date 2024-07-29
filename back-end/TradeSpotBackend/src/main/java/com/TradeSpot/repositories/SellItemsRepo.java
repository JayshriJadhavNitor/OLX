package com.TradeSpot.repositories;

import com.TradeSpot.entities.SellItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SellItemsRepo extends JpaRepository<SellItems, Long> {

    @Query("SELECT COUNT(DISTINCT p.seller) FROM SellItems p")
    long findSellerCount();
}
