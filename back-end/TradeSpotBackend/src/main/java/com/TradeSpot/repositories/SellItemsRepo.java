package com.TradeSpot.repositories;

import com.TradeSpot.entities.SellItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellItemsRepo extends JpaRepository<SellItems, Long> {
}
