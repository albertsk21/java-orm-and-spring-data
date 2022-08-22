package productShop.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productShop.dto.xml.input.CategoriesRootDto;
import productShop.dto.xml.input.CategoryXmlDto;
import productShop.dto.xml.output.query3.CategoriesRootXml;
import productShop.models.Category;
import productShop.paths.input.InputPaths;
import productShop.paths.output.OutputPaths;
import productShop.repositories.CategoryRepository;
import productShop.services.interfaces.CategoryService;
import productShop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(Gson gson, ModelMapper modelMapper, XmlParser xmlParser, CategoryRepository categoryRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void registerFromXml() throws JAXBException {
        CategoriesRootDto categoriesRootDto = this.xmlParser.parseXml(CategoriesRootDto.class, InputPaths.CATEGORIES_FILE_XML_PATH);
        for ( CategoryXmlDto categoryXmlDto : categoriesRootDto.getCategories() ) {
            Category category = this.modelMapper.map(categoryXmlDto,Category.class);
            this.categoryRepository.saveAndFlush(category);
        }
    }


        @Override
        public void exportCategoriesByProducts() throws JAXBException, FileNotFoundException {
        CategoriesRootXml categoriesRootXml = this.extractCategories();
        this.xmlParser.parseOutput(categoriesRootXml, OutputPaths.CATEGORIES_BY_PRODUCTS_XML_PATH);
    }

    public CategoriesRootXml extractCategories(){


        CategoriesRootXml categoriesRootDto = new CategoriesRootXml();


        List<Category> categories =  this.categoryRepository.getAllCategoriesByCountProduct();
        for (Category category : categories) {

            productShop.dto.xml.output.query3.CategoryXmlDto categoryXmlDto = new productShop.dto.xml.output.query3.CategoryXmlDto();

            categoriesRootDto.getCategories().add(categoryXmlDto);

        }



        return categoriesRootDto;
    }
}
