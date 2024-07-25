package com.TradeSpot.services;

import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.SellItems;
import com.TradeSpot.entities.User;
import com.TradeSpot.repositories.SellItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellItemService {

    @Autowired
    private SellItemsRepo sellrepo;

    public SellItems addSellProduct(User user, Product product){
        SellItems sellItems=new SellItems();
        sellItems.setSeller(user);
        sellItems.setSellproduct(product);

        return sellrepo.save(sellItems);

    }
}
