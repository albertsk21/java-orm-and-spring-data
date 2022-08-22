package productShop.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface ProductService {
    void registerFromXml() throws JAXBException;
    void insertRandomCategoryForProducts();

    void insertInXmlProductInRange() throws JAXBException, FileNotFoundException;
}
