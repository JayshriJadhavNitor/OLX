package com.TradeSpot.controllers;

import com.TradeSpot.DTO.CategoryDTO;
import com.TradeSpot.DTO.ProductDTO;
import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.DTO.UserDTO;
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



//    @PostMapping(path="/{categoryName}/{userId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ApiResponse> addProduct(
//            @PathVariable String categoryName,
//            @PathVariable long userId,
//            @ModelAttribute ProductDTO productDTO,
//            @RequestPart("image") MultipartFile file) throws IOException {
//        Product product= productService.saveProduct(productDTO, categoryName, userId, file);
//        if(product!=null){
//            return ResponseEntity.ok().body(new ApiResponse("product added successfully"));
//        }
//        else{
//            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: product not added"));
//        }
//
//
//
//    }

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
    public ResponseEntity<?> findProductById(@PathVariable long productId) throws CustomException {

        ProductResponseDTO productDTO= productService.getProductById(productId);
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

    @PutMapping(path = "/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(@PathVariable long productId,
                                           @ModelAttribute ProductDTO productDTO,
                                           @RequestPart MultipartFile file) throws IOException {

        Product product= productService.updateProduct(productId, productDTO, file);

        if(product != null){
            return ResponseEntity.ok().body(new ApiResponse("Product updated successfully with id  "+ productId));

        }
        else{
            return ResponseEntity.status(500).body(new ApiResponse("Unsuccessful: not updated with id "+productId));
        }
    }


    @PostMapping(path="/{userId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addProduct(
            @PathVariable long userId,
            @ModelAttribute ProductDTO productDTO,
            @RequestParam String categoryName,
            @RequestPart("image") MultipartFile file) throws IOException {


        Product product= productService.saveProduct(productDTO, categoryName, userId, file);
        if(product!=null){
            return ResponseEntity.ok().body(new ApiResponse("product added successfully"));
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: product not added"));
        }



    }

    @GetMapping("/getproductbycategory/{categoryName}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable String categoryName){

        List<ProductResponseDTO> dtoList=productService.findProductsByCategoryName(categoryName);
        if(dtoList!=null){
            return ResponseEntity.ok().body(dtoList);
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping("/getbuyproduct/{id}")
    public ResponseEntity<?> getBroughtitmes(@PathVariable long id){
         List<ProductResponseDTO> list = productService.getBuyItems(id);

        if(list!=null){
            return ResponseEntity.ok().body(list);
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping("/getsellproduct/{id}")
    public ResponseEntity<?> getSellItmes(@PathVariable long id){
        List<ProductResponseDTO> list = productService.getListedProduct(id);

        if(list!=null){
            return ResponseEntity.ok().body(list);
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping(path = "/activeproducts")
    public ResponseEntity<?> getActiveProducts(){
        List<ProductResponseDTO> list = productService.findActiveProducts();

        if(list!=null){
            return ResponseEntity.ok().body(list);
        }
        else{
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping("/getproducts/{userId}")
    public ResponseEntity<?> otherUserProducts(@PathVariable long userId){

        List<ProductResponseDTO> list = productService.findOtherUsersProducts(userId);

        if(!list.isEmpty()){
            return ResponseEntity.ok().body(list);
        }
        else{
            System.out.println("in empty mail");
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping(path = "/getproductsbyuserId/{userId}")
    public ResponseEntity<?> getActiveProductsByUserId(@PathVariable long userId){

        List<ProductResponseDTO> list = productService.findActiveProductsByUserId(userId);

        if(!list.isEmpty()){
            return ResponseEntity.ok().body(list);
        }
        else{
            System.out.println("in empty mail");
            return  ResponseEntity.ok().body(new ApiResponse("unsuccessful: products not find"));
        }
    }

    @GetMapping("/getSellerCount")
    public ResponseEntity<?> getSellerCount(){

        long count = productService.getSellerCount();
        return  ResponseEntity.ok().body(count);
    }


    @GetMapping(path="/getSeller/{productId}")
    public  ResponseEntity<?> getSeller(@PathVariable long productId){

        UserDTO seller = productService.findSeller(productId);

        return ResponseEntity.ok().body(seller);
    }



    @GetMapping("/contactSeller/{userId}/{productId}")
    public ResponseEntity<?> notifySeller(@PathVariable Long userId, @PathVariable Long productId ){

        String msg = productService.sendNotification(userId, productId);

        return  ResponseEntity.ok().body(msg);
    }








}
