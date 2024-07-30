package com.TradeSpot.services;

import com.TradeSpot.DTO.CategoryDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.ClientInfoStatus;
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
        System.out.println(category);
        User user = userRepository.findById(userId).orElseThrow();


        Product product=mapper.map(productDTO, Product.class);
        product.setProductImgPath(filePath);
        product.setCategory(category);
        product.setActive(true);
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

    public Product upgradeProduct(long productId, ProductDTO productDTO) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found with id " + productId));
        product.setActive(true);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setAddedDate(productDTO.getAddedDate());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);

    }

    public List<ProductResponseDTO> findProductsByCategoryName(String categoryName) {

        List<Product> list = productRepository.getProductByCategoryName(categoryName);
        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).toList();
    }

    public List<ProductResponseDTO> getBuyitems(long id){
        List<Product> products= broughtItemsServices.productList(id);

        return products.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).toList();

    }


    public List<ProductResponseDTO> getSellitems(long id) {

        List<Product> list = sellItemsServices.findSellingList(id);
        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }


    public List<ProductResponseDTO> findActiveProducts() {

        List<Product> list = productRepository.findAll();
        return  list.stream().filter(product -> product.isActive())
                .map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }
    public List<ProductResponseDTO> findproducts(long id) {

        List<Product> list= sellItemsServices.findProduct(id);

        return list.stream().filter(Product::isActive).map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findproductsbyuserId(long id) {

        List<Product> list= sellItemsServices.findProductsByUserId(id);

        return list.stream().filter(Product::isActive).map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }
}
