package com.TradeSpot.controllers;


import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.services.SellItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SellItemController {

    @Autowired
    private SellItemService sellItemService;

    @GetMapping(path= "/sellproductsbyuser/{userid}")
    public List<ProductResponseDTO> getSellProductsByUserId(@PathVariable long userid){

        return sellItemService.productList(userid);
    }
}
