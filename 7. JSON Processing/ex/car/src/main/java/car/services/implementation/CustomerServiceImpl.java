package car.services.implementation;

import car.dto.CustomerDto;
import car.models.Customer;
import car.pojo.CustomerPojo;
import car.repositories.CustomerRepository;
import car.services.interfaces.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final String CUSTOMERS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\input\\customers.json";
    private final String ORDERED_CUSTOMERS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\output\\ordered-customers.json";

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    private String readFilesFromJson() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_PATH));
    }

    @Override
    public void exportAllCustomersOrderByBirthDayAscending() throws IOException {




    }

    private CustomerDto[] getCustomerDtoOrderByBirthdateASC(){
        Customer[] customers = this.customerRepository.getAllCustomerByBirthdayAscOrder();
        CustomerDto[] customersDto = this.modelMapper.map(customers,CustomerDto[].class);
        return customersDto;
    }



    @Override
    public void importCustomersFromJson() throws IOException {

    }


}
