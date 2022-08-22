package car.services.interfaces;

import java.io.IOException;

public interface SupplierService {


    void importAllSuppliersFromJson() throws IOException;


    void exportAllLocalSuppliers() throws IOException;
}
