package com.TradeSpot.services;

import com.TradeSpot.DTO.ProductDTO;
import com.TradeSpot.DTO.ProductResponseDTO;
import com.TradeSpot.customException.CustomException;
import com.TradeSpot.entities.Category;
import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.User;
import com.TradeSpot.repositories.ProductRepository;
import com.TradeSpot.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Imageservice imageservice;

    @Autowired
    private  SellItemService sellItemsServices;

    @Autowired
    private BroughtItemService broughtItemsServices;

    public Product saveProduct(ProductDTO productDTO, String categoryName, Long userId, MultipartFile file) throws IOException {

        String filePath= imageservice.uploadFile(file, "Product");


        Category category= categoryService.findByName(categoryName);
        User user = userRepository.findById(userId).orElseThrow();

//        Product product= Product.builder()
//                        .productName(productDTO.getProductName())
//                                .productImgPath(filePath)
//                                        .price(productDTO.getPrice())
//                                                .addedDate(productDTO.getAddedDate())
//                                                        .description(productDTO.getDescription())
//                                                                .isActive(true)
//                                                                        .build();
        Product product=mapper.map(productDTO, Product.class);
        product.setProductImgPath(filePath);
        product.setCategory(category);
        product=productRepository.save(product);
         sellItemsServices.addSellProduct(user,product);

         return product;




    }

    public List<ProductResponseDTO> getALlProducts() {

        List<Product> productList= productRepository.findAll();
        return productList.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public ProductDTO getProductById(long productId) throws CustomException {
        Product product = productRepository.findById(productId)
                                     .orElseThrow(()-> new CustomException("Product not found with id : "+ productId));

        return mapper.map(product, ProductDTO.class);

    }

    public String deleteProduct(long productId) throws CustomException {
        Product product = productRepository.findById(productId)
                                     .orElseThrow(()-> new CustomException("Product not found with id : "+ productId));

        product.setCategory(null);

        productRepository.deleteById(productId);
        return "Product deleted successfully";


    }

    public void buyProduct(long userid, long productid) {

        User user=userRepository.findById(userid).orElseThrow();
        Product product=productRepository.findById(productid).orElseThrow();
        product.setActive(false);
        product=productRepository.save(product);

        broughtItemsServices.buyProduct(user,product);





    }
}
