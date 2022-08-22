package car.services.interfaces;

import java.io.IOException;

public interface CarService {


    void ImportCarsFromJson() throws IOException;

    void getAllCarsMakeByToyota() throws IOException;

    void exportAllCarsWithParts() throws IOException;
}
