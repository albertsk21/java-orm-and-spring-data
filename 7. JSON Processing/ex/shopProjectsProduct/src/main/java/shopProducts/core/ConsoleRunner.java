package shopProducts.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import shopProducts.services.interfaces.CategoryService;
import shopProducts.services.interfaces.ProductService;
import shopProducts.services.interfaces.UserService;

import java.io.IOException;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;

    public ConsoleRunner(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws IOException {


     this.categoryService.getAllCategories();




    }
}
