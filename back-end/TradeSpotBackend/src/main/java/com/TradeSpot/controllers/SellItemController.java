package com.TradeSpot.controllers;


import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.entities.ApiResponse;
import com.TradeSpot.services.SellItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

//    @GetMapping("/getSellingProducts/{userId}")
//    public ResponseEntity<?> getSellingProducts(@PathVariable long userId){
//
//        List<ProductResponseDTO> responseDTOList= sellItemService.productList(userId);
//
//        if(responseDTOList!=null){
//            return ResponseEntity.ok().body(responseDTOList);
//        }
//        else{
//            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
//        }
//
//    }
}
