package car.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SaleService {
    void insertRandomSales();

    void getInformationAboutSales();

    void getInformationAboutSalesXml() throws JAXBException, FileNotFoundException;
}
