package com.TradeSpot.controllers;


import com.TradeSpot.DTO.CategoryDTO;
import com.TradeSpot.DTO.CategoryResponseDTO;
import com.TradeSpot.entities.ApiResponse;
import com.TradeSpot.entities.Category;
import com.TradeSpot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> addCategory(
            @ModelAttribute CategoryDTO categoryDTO,
            @RequestPart MultipartFile file) throws IOException {
        Category category= categoryService.saveCategory(categoryDTO, file);
        if(category!= null){
            return  ResponseEntity.ok().body(new ApiResponse("Category added successfully"));
        }

        else {
            return  ResponseEntity.status(500).body(new ApiResponse("Unsuccessful: Category not added"));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        List<CategoryResponseDTO> categoryDTOS = categoryService.findAllCategories();
        if(categoryDTOS!= null){
            return  ResponseEntity.ok( categoryDTOS);
        }

        else {
            return  ResponseEntity.status(500).build();
        }

    }

    @GetMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> getAllCategories(@PathVariable long id){
        CategoryDTO categoryDTO = categoryService.findCategory(id);
        if(categoryDTO!= null){
            return  ResponseEntity.ok( categoryDTO);
        }

        else {
            return  ResponseEntity.status(500).build();
        }

    }

    @DeleteMapping( path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable long id){

         return ResponseEntity.ok().body(new ApiResponse(categoryService.DeleteCategory(id)));


    }




}
