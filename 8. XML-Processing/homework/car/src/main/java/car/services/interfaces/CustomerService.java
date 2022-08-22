package car.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CustomerService {

     void exportAllCustomersOrderByBirthDayAscending() throws IOException;
     void importCustomersFromJson() throws IOException;

    void importCustomersFromXml() throws IOException, JAXBException;

    void importCustomersOrderByBirthDateXml() throws JAXBException, FileNotFoundException;
}
