package car.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PartService {

    void importAllPartsFromJson() throws IOException;

    void importAllPartsFromXml() throws JAXBException;
}
