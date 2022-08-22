package productShop.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import productShop.services.interfaces.CategoryService;
import productShop.services.interfaces.ProductService;
import productShop.services.interfaces.UserService;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;
    public ConsoleRunner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {


        this.categoryService.exportCategoriesByProducts();

    }
}
