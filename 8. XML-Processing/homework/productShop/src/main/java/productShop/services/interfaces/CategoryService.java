package productShop.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CategoryService {



    void registerFromXml() throws JAXBException;

    void exportCategoriesByProducts() throws JAXBException, FileNotFoundException;
}
