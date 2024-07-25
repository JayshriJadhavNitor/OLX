package com.TradeSpot.controllers;

import com.TradeSpot.DTO.ProductDTO;
import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.customException.CustomException;
import com.TradeSpot.entities.ApiResponse;
import com.TradeSpot.entities.Product;
import com.TradeSpot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path= "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @PostMapping(path = "/{categoryName}/{userId}")
//    public void sellProduct(@PathVariable String categoryName,@PathVariable Long userId, @RequestBody ProductDTO productDTO){
//        productService.saveProduct(productDTO,categoryName, userId);
//    }



    @PostMapping(path="/{categoryName}/{userId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addProduct(
            @PathVariable String categoryName,
            @PathVariable long userId,
            @ModelAttribute ProductDTO productDTO,
            @RequestPart("image") MultipartFile file) throws IOException {
        Product product= productService.saveProduct(productDTO, categoryName, userId, file);
        if(product!=null){
            return ResponseEntity.ok().body(new ApiResponse("product added successfully"));
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: product not added"));
        }



    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResponseDTO>> listAllProduct() throws CustomException {

        List<ProductResponseDTO> productResponseDTO= productService.getALlProducts();
        if(productResponseDTO!=null){
            return ResponseEntity.ok(productResponseDTO);
        }
        else{
            throw new CustomException("list is empty");

        }
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findProductById(@PathVariable long productId) throws CustomException {

        ProductDTO productDTO= productService.getProductById(productId);
        if(productDTO!=null){
            return ResponseEntity.ok(productDTO);
        }
        else{
            return ResponseEntity.notFound().build();

        }
    }

    @PostMapping(path = "buyproduct/{userid}/{productid}")
    public void buyProduct(@PathVariable long userid, @PathVariable long productid){

        productService.buyProduct(userid, productid);

    }

    @DeleteMapping (path = "/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable long productId) throws CustomException {

        String message = productService.deleteProduct(productId);
        return ResponseEntity.ok().body(new ApiResponse(message));

    }

}
