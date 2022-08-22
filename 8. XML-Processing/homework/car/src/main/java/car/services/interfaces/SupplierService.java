package car.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SupplierService {


    void importAllSuppliersFromJson() throws IOException;


    void exportAllLocalSuppliers() throws IOException;

    void importSuppliersFromXml() throws JAXBException;

    void exportAllLocalSuppliersXml() throws JAXBException, FileNotFoundException;
}
