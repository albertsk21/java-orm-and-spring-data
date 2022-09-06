package softuni.workshop.service.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CompanyService {

    void importCompanies() throws JAXBException, IllegalAccessException, InstantiationException;

    boolean areImported();

    String readCompaniesXmlFile() throws IOException;
}
