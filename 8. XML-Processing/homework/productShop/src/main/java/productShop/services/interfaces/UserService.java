package productShop.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {



    void registerFromJson() throws IOException;
    void registerFromXml() throws JAXBException;

    void insertDataInUsersSoldProductXml() throws JAXBException, FileNotFoundException;
}
