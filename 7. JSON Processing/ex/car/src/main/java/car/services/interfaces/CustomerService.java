package car.services.interfaces;

import java.io.IOException;

public interface CustomerService {

     void exportAllCustomersOrderByBirthDayAscending() throws IOException;
     void importCustomersFromJson() throws IOException;
}
