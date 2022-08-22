package shopProducts.services.implementation;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shopProducts.dto.CategoryDto;
import shopProducts.models.Category;
import shopProducts.repositories.CategoryRepository;
import shopProducts.services.interfaces.CategoryService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
        private CategoryRepository categoryRepository;
        private Gson gson;
        private ModelMapper modelMapper;
        private final String CATEGORY_PATH = "src/main/resources/static/files/json/categories.json";
        private final String CATEGORY_BY_PRODUCTS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\shopProjectsProduct\\src\\main\\resources\\static\\files\\json\\output\\categories-by-products.json";
    public CategoryServiceImpl(CategoryRepository categoryService, Gson gson, ModelMapper modelMapper) {
        this.categoryRepository = categoryService;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    private String readFilesFromJson() throws IOException {
        return Files.readString(Path.of(CATEGORY_PATH));
    }


    @Override
    public void registerCategories() throws IOException {
        CategoryDto[] categoriesDto = this.gson.fromJson(this.readFilesFromJson(),CategoryDto[].class);


        for (CategoryDto categoryDto : categoriesDto) {
            Category category = this.modelMapper.map(categoryDto,Category.class);
            this.categoryRepository.save(category);
        }

    }



    @Override
    public void getAllCategories() throws IOException {


        List<CategoryDto> categoriesDto = new ArrayList<>();
        List<Object[]> objectsList = this.categoryRepository.getAllCategories();
        List<Category> categories = this.categoryRepository.findAll();
        for (Object[] objects : objectsList) {

            String name = String.valueOf(objects[0]);
            int productsCount = Integer.parseInt(String.valueOf(objects[1]));
            BigDecimal averagePrice = new BigDecimal(String.valueOf(objects[2]));
            BigDecimal totalRevenue = new BigDecimal(String.valueOf(objects[3]));

            CategoryDto categoryDto = new CategoryDto(name,productsCount,averagePrice,totalRevenue);
            categoriesDto.add(categoryDto);

        }

        String content = this.gson.toJson(categoriesDto);
        FileWriter fileWriter = new FileWriter(CATEGORY_BY_PRODUCTS_PATH);

        fileWriter.write(content);
        fileWriter.close();


    }

}
