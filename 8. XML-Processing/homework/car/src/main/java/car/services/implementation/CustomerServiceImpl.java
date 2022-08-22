package car.services.implementation;

import car.dto.json.CustomerDto;
import car.dto.xml.input.CustomerXmlInput;
import car.dto.xml.input.CustomersRootXmlInput;
import car.dto.xml.output.query1.CustomerXmlOutput;
import car.dto.xml.output.query1.CustomersRootOutput;
import car.models.Customer;
import car.paths.input.Paths;
import car.repositories.CustomerRepository;
import car.services.interfaces.CustomerService;
import car.util.interfaces.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;


@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;

    public CustomerServiceImpl(CustomerRepository customerRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void exportAllCustomersOrderByBirthDayAscending() throws IOException {
    }
    @Override
    public void importCustomersFromJson() throws IOException {
    }
    @Override
    public void importCustomersFromXml() throws IOException, JAXBException {
        CustomersRootXmlInput customersRootXmlInput = this.xmlParser.parseXml(CustomersRootXmlInput.class, Paths.CUSTOMERS_PATH_XML);
        for (CustomerXmlInput customerXmlInput : customersRootXmlInput.getCustomerXmlInputs()) {
            Customer customer = this.convertCustomerXmlInput(customerXmlInput);
            this.customerRepository.saveAndFlush(customer);
        }

    }


    @Override
    public void importCustomersOrderByBirthDateXml() throws JAXBException, FileNotFoundException {


        CustomersRootOutput customersRootOutput = new CustomersRootOutput();


        for (Customer customer : this.customerRepository.getAllCustomerByBirthdayAscOrder()) {

            CustomerXmlOutput customerXmlOutput = new CustomerXmlOutput();
            customerXmlOutput.setId(customer.getId());
            customerXmlOutput.setName(customer.getName());
            customerXmlOutput.setYoungDriver(customer.IsYoungDriver());
            customerXmlOutput.setBirthDate(customer.getBirthDate().toString());
            customersRootOutput.getCustomers().add(customerXmlOutput);


        }




        this.xmlParser.parseOutput(customersRootOutput,Paths.ORDERED_CUSTOMERS_PATH_XML);

    }


    private String readFilesFromJson() throws IOException {
        return Files.readString(Path.of(Paths.CUSTOMERS_PATH_JSON));
    }
    private Customer convertCustomerXmlInput(CustomerXmlInput customerXmlInput){
        Customer customer = new Customer();
        String[] date = customerXmlInput.getBirthDate().split("T")[0].split("-");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        customer.setName(customerXmlInput.getName());
        customer.setYoungDriver(customerXmlInput.isYoungDriver());
        customer.setBirthDate(birthDate);

        return customer;
    }
    private CustomerDto[] getCustomerDtoOrderByBirthdateASC(){
        Customer[] customers = this.customerRepository.getAllCustomerByBirthdayAscOrder();
        CustomerDto[] customersDto = this.modelMapper.map(customers,CustomerDto[].class);
        return customersDto;
    }

}
