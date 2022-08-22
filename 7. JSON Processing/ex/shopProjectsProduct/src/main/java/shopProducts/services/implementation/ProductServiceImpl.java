package shopProducts.services.implementation;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shopProducts.dto.ProductDto;
import shopProducts.models.Category;
import shopProducts.models.Product;
import shopProducts.models.User;
import shopProducts.pojo.ProductPojo;
import shopProducts.repositories.CategoryRepository;
import shopProducts.repositories.ProductRepository;
import shopProducts.repositories.UserRepository;
import shopProducts.services.interfaces.ProductService;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    private final String PRODUCT_PATH = "src/main/resources/static/files/json/products.json";
    private final String SENT_DATA_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\shopProjectsProduct\\src\\main\\resources\\static\\files\\json\\products-in-range.json";
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private Gson gson;
    private ModelMapper modelMapper;


    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository, Gson gson, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    private String readFileFromFromJson() throws IOException {
        return Files.readString(Path.of(PRODUCT_PATH));
    }
    @Override
    public void registerProducts() throws IOException {
        ProductDto[] productsDto = this.gson.fromJson(this.readFileFromFromJson(),ProductDto[].class);

        for (ProductDto productDto : productsDto) {

            User buyer = this.generateRandomUser();
            User seller = this.generateRandomUser();
            Category category = this.generateRandomCategory();


            Product product = this.modelMapper.map(productDto,Product.class);
            this.productRepository.save(product);
        }
    }
    private User generateRandomUser(){
        Random random = new Random();
        int randomId = random.nextInt((int) this.userRepository.count());
        return this.userRepository.findUserById((long) randomId);
    }
    private Category generateRandomCategory(){
        Random random = new Random();
        int randomId = random.nextInt((int) this.categoryRepository.count());
        return this.categoryRepository.findCategoryById((long) randomId);
    }

    @Override
    public void getAllProductsWithRange() throws IOException {




    }

}
