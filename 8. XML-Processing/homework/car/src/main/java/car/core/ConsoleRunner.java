package car.core;


import car.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private SupplierService supplierService;
    private PartService partService;
    private CarService carService;
    private CustomerService customerService;
    private SaleService saleService;
    public ConsoleRunner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {


        this.saleService.getInformationAboutSalesXml();

    }
}
