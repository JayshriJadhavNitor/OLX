package com.TradeSpot.services;

import com.TradeSpot.DTO.*;
import com.TradeSpot.customException.CustomException;
import com.TradeSpot.entities.Category;
import com.TradeSpot.entities.Product;
import com.TradeSpot.entities.User;
import com.TradeSpot.repositories.ProductRepository;
import com.TradeSpot.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;




    @Autowired
    private BroughtItemService broughtItemsServices;

    @Transactional
    public Product saveProduct(ProductDTO productDTO, String categoryName, Long userId, MultipartFile file) throws IOException {

        Category category= categoryService.findByName(categoryName);

        User user = userRepository.findById(userId).orElseThrow();
        String filePath= imageservice.uploadFile(file);


        Product product=mapper.map(productDTO, Product.class);
        product.setProductImgPath(filePath);
        product.setCategory(category);
        product.setUser(user);
        product.setActive(true);
        product=productRepository.save(product);


         return product;




    }

    public List<ProductResponseDTO> getALlProducts() {

        List<Product> productList= productRepository.findAll();
        return productList.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public ProductResponseDTO getProductById(long productId) throws CustomException {
        Product product = productRepository.findById(productId)
                                     .orElseThrow(()-> new CustomException("Product not found with id : "+ productId));

        return mapper.map(product, ProductResponseDTO.class);

    }

    public String deleteProduct(long productId) throws CustomException {
        Product product = productRepository.findById(productId)
                                     .orElseThrow(()-> new CustomException("Product not found with id : "+ productId));



        productRepository.deleteById(productId);
        return "Product deleted successfully";


    }

    @Transactional
    public void buyProduct(long userid, long productid) {

        User user=userRepository.findById(userid).orElseThrow();
        Product product=productRepository.findById(productid).orElseThrow();
        product.setActive(false);
        product=productRepository.save(product);

        broughtItemsServices.buyProduct(user,product);





    }

    public Product updateProduct(long productId, ProductDTO productDTO, MultipartFile file) throws IOException {

        Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found with id " + productId));
        product.setActive(true);
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setAddedDate(productDTO.getAddedDate());
        product.setPrice(productDTO.getPrice());

        String filePath = imageservice.uploadFile(file);

        product.setProductImgPath(filePath);
       // product = productRepository.save(product);

        return productRepository.save(product);

    }

    public List<ProductResponseDTO> findProductsByCategoryName(String categoryName) {

        List<Product> list = productRepository.getProductByCategoryName(categoryName);
        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).toList();
    }

    public List<ProductResponseDTO> getBuyItems(long userId){
        List<Product> products= broughtItemsServices.productList(userId);

        return products.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).toList();

    }

    @Transactional
    public List<ProductResponseDTO> getListedProduct(long userId) {

        List<Product> list = userService.listUserProducts(userId);
        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }


    public List<ProductResponseDTO> findActiveProducts() {


        List<Product> productList =  productRepository.getActiveProducts();
        return  productList.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public List<ProductResponseDTO> findOtherUsersProducts(long userId) {

        List<Product> list = productRepository.listOthersProducts( userId);

        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public List<ProductResponseDTO> findActiveProductsByUserId(long userId) {

        List<Product> list= productRepository.listUserActiveProducts(userId);

        return list.stream().map(product -> mapper.map(product, ProductResponseDTO.class)).collect(Collectors.toList());
    }

    public long getSellerCount() {

        return productRepository.getSellerCount();
    }

    @Transactional
    public UserDTO findSeller(long productId) {

        Product product = productRepository.findById(productId).orElseThrow();
        return mapper.map(product.getUser(), UserDTO.class);
    }


    @Transactional
    public String sendNotification(Long userId, long productId) {




        try {


            Product product = productRepository.findById(productId).orElseThrow();
            UserDTO user = userService.findUser(userId);
            UserUpdateDTO userUpdateDTO = mapper.map(user, UserUpdateDTO.class);

            String str = buildEmailBody(product.getUser().getFirstName()+" "+product.getUser().getLastName(),product.getProductName(),product.getDescription(),
                    user.getFirstName()+" "+ user.getLastName(),user.getEmail(),user.getAddress());
            SimpleMailMessage message = new SimpleMailMessage();
            System.out.println(product.getUser().getEmail());
            message.setTo(product.getUser().getEmail());
            message.setSubject("buyer notification");
            System.out.println(userUpdateDTO.toString());
            message.setText(str);
            javaMailSender.send(message);
            return "mail sent successfully";

        }catch (MailException e){
            e.printStackTrace();
        }
        //return "mail sent successfully";
        return "mail sent successfully";
    }

    public static String buildEmailBody(String sellerName, String productName,  String productDescription, String buyerName, String buyerEmail, String buyerLocation) {
        return String.format(
                "Dear %s,%n%n" +
                        "I hope this message finds you well.%n%n" +
                        "We are excited to inform you that a buyer has shown interest in your product. Below are the details of the buyer and the product they are interested in:%n%n" +
                        "Product Information:%n" +
                        "- Product Name: %s%n" +

                        "- Description: %s%n%n" +
                        "Buyer Information:%n" +
                        "- Name: %s%n" +
                        "- Email: %s%n" +
                        "- Location: %s%n%n" +
                        "We encourage you to review these details and reach out to the buyer to discuss further steps. If you have any questions or need assistance, please do not hesitate to contact us.%n%n" +
                        "Thank you for your attention, and best of luck with your sale!%n%n" +
                        "Warm regards,%n" +
                        "OLX",

                sellerName,
                productName,

                productDescription,
                buyerName,
                buyerEmail,
                buyerLocation
        );
    }
}
