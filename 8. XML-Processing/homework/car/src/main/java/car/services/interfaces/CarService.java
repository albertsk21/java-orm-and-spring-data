package car.services.interfaces;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CarService {


    void ImportCarsFromJson() throws IOException;

    void getAllCarsMakeByToyota() throws IOException;

    void exportAllCarsWithParts() throws IOException;

    void ImportCarsFromXml() throws JAXBException;

    void getAllCarsMakeByToyotaXml() throws JAXBException, FileNotFoundException;

    void exportAllCarsWithPartsXml() throws JAXBException, FileNotFoundException;
}
