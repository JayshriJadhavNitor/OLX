package com.TradeSpot.services;

import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.SellItems;
import com.TradeSpot.entities.User;
import com.TradeSpot.repositories.SellItemsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SellItemService {

    @Autowired
    private SellItemsRepo sellrepo;

    @Autowired
    private ModelMapper mapper;

    public SellItems addSellProduct(User user, Product product){
        SellItems sellItems=new SellItems();
        sellItems.setSeller(user);
        sellItems.setSellproduct(product);

        return sellrepo.save(sellItems);

    }


    public List<Product> findSellingList(long id) {

        return sellrepo.findSellProduct(id);
    }
}
