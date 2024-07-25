package com.TradeSpot.services;

import com.TradeSpot.DTO.CategoryDTO;
import com.TradeSpot.DTO.CategoryResponseDTO;
import com.TradeSpot.entities.Category;
import com.TradeSpot.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Imageservice imageservice;

    public Category saveCategory(CategoryDTO categoryDTO, MultipartFile file) throws IOException {

        String imgPath= imageservice.uploadFile(file, "Category");

//
        Category category= Category.builder().name(categoryDTO.getName())
                .categoryImgPath(imgPath).build();
        return categoryRepository.save(category);
    }

    public List<CategoryResponseDTO> findAllCategories() {

        List<Category> categories= categoryRepository.findAll();
        return categories.stream().map(category -> mapper.map(category, CategoryResponseDTO.class)).collect(Collectors.toList());
    }

    public CategoryDTO findCategory(Long id) {

        Category category= categoryRepository.findById(id).orElseThrow();
        return mapper.map(category, CategoryDTO.class);
    }

    public String DeleteCategory(long id) {
        Category category= categoryRepository.findById(id).orElseThrow();
        categoryRepository.deleteById(id);
        return "Deleted successfully";


    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
}
