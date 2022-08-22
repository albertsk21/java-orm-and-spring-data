package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.xml.ProductRootXml;
import hiberspring.domain.dtos.xml.ProductXml;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.filepaths.FilesPaths;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
@Service
public class ProductServiceImpl implements ProductService {
    private BranchRepository branchRepository;
    private ProductRepository productRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private FileUtil fileUtil;
    public ProductServiceImpl(BranchRepository branchRepository, ProductRepository productRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil) {
        this.branchRepository = branchRepository;
        this.productRepository = productRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }
    @Override
    public Boolean productsAreImported() {
        return (int) this.productRepository.count() > 0;
    }
    @Override
    public String readProductsXmlFile() throws IOException {
        return this.fileUtil.readFile(FilesPaths.PRODUCTS_XML);
    }
    @Override
    public String importProducts() throws JAXBException {
        ProductRootXml productsXml = this.xmlParser.parseXml(ProductRootXml.class, FilesPaths.PRODUCTS_XML);
        StringBuilder output = new StringBuilder();
        for (ProductXml productXml : productsXml.getProducts()) {
            try {
                this.detectIsValid(productXml);
                Branch branch = this.getBranchByName(productXml.getBranch());
                Product product = this.modelMapper.map(productXml,Product.class);
                product.setBranches(branch);
                this.productRepository.saveAndFlush(product);
                output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,Product.class.getSimpleName(),product.getName()));

            }catch (IllegalArgumentException | InstantiationException | IllegalAccessException e){
                output.append(Constants.INCORRECT_DATA_MESSAGE);
            }
        }
        return output.toString();
    }
    private void detectIsValid(ProductXml product) throws InstantiationException, IllegalAccessException {
        if(!this.validationUtil.isValid(product)){
            throw new IllegalArgumentException();
        }
    }
    private Branch getBranchByName(String name){
        Branch branch = this.branchRepository.getBranchByName(name);
        if(branch == null){
            throw new IllegalArgumentException();
        }
        return  branch;
    }
}
