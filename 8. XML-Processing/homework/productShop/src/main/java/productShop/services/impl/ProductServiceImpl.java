package productShop.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productShop.dto.xml.input.ProductXmlDto;
import productShop.dto.xml.input.ProductsRootDto;
import productShop.dto.xml.output.query1.ProductRootOutput;
import productShop.dto.xml.output.query1.ProductXmlDtoOutput;
import productShop.models.Category;
import productShop.models.Product;
import productShop.models.User;
import productShop.paths.input.InputPaths;
import productShop.paths.output.OutputPaths;
import productShop.repositories.CategoryRepository;
import productShop.repositories.ProductRepository;
import productShop.repositories.UserRepository;
import productShop.services.interfaces.ProductService;
import productShop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private Gson gson;
    private Random random;
    private CategoryRepository categoryRepository;


    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ModelMapper modelMapper, XmlParser xmlParser, Gson gson, Random random, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.gson = gson;
        this.random = random;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void registerFromXml() throws JAXBException {

        ProductsRootDto productsRootDto = this.xmlParser.parseXml(ProductsRootDto.class, InputPaths.PRODUCT_FILE_XML_PATH);


        for (ProductXmlDto productXmlDto : productsRootDto.getProducts()) {

            Product product = this.modelMapper.map(productXmlDto,Product.class);

            User randomBuyer = this.getRandomUser();
            User randomSeller = this.getRandomUser();


            product.setBuyer(randomBuyer);
            product.setSeller(randomSeller);



            this.productRepository.saveAndFlush(product);


        }

    }
    @Override
    public void insertRandomCategoryForProducts() {
        for (Product product : this.productRepository.findAll().toArray(Product[]::new)) {
            Category category = this.getRandomCategory();
            product.getCategories().add(category);
            this.productRepository.save(product);
        }
    }


    @Override
    public void insertInXmlProductInRange() throws JAXBException, FileNotFoundException {
        ProductRootOutput productRootOutput = this.convertProductsInObjectXml(this.productRepository.getAllProductsWithOfPrice());
        this.xmlParser.parseOutput(productRootOutput, OutputPaths.PRODUCTS_IN_RANGE_XML_PATH);
    }



    private ProductRootOutput convertProductsInObjectXml(Product[] products){
        ProductRootOutput productRootOutput = new ProductRootOutput();
        for (Product product : products) {
            ProductXmlDtoOutput productXmlDtoOutput = new ProductXmlDtoOutput();

            productXmlDtoOutput.setName(product.getName());
            productXmlDtoOutput.setPrice(product.getPrice());
            productXmlDtoOutput.setSeller(String.format("%s %s",product.getSeller().getFirstName(),product.getSeller().getLastName()));


            productRootOutput.getProducts().add(productXmlDtoOutput);

        }
        return productRootOutput;
    }
    private User getRandomUser(){
        int countUsers = this.userRepository.getLengthUser();
        Long randomId = random.nextLong(countUsers);
        return this.userRepository.getUserById(randomId);
    }
    private Category getRandomCategory(){
        int lengthCategories = this.categoryRepository.getLengthCategories();
        Long randomId = this.random.nextLong(lengthCategories);
        return this.categoryRepository.getCategoryById(randomId);

    }
}
