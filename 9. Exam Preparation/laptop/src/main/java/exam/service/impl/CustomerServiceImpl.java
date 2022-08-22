package exam.service.impl;

import com.google.gson.Gson;
import exam.config.interfaces.XmlParser;
import exam.data.entity.json.CustomerDtoInputJson;
import exam.data.model.Customer;
import exam.data.model.Town;
import exam.path.PathsProject;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private CustomerRepository customerRepository;
    private TownRepository townRepository;
    private Gson gson;


    public CustomerServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, CustomerRepository customerRepository, TownRepository townRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(PathsProject.CUSTOMERS_JSON));
    }

    @Override
    public String importCustomers() throws IOException {
        CustomerDtoInputJson[] customersJson = this.gson.fromJson(this.readCustomersFileContent(), CustomerDtoInputJson[].class);


        StringBuilder output = new StringBuilder();
        for (CustomerDtoInputJson customerJson : customersJson) {
            try {

                Customer customer = this.convertCustomerJson(customerJson);
                customer.setTown(this.findTown(customerJson.getTown().getName()));
                this.customerRepository.saveAndFlush(customer);

                output.append(String.format("Successfully imported Customer %s %s - %s\n",customer.getFirstName(),customer.getLastName(),customer.getEmail()));
            }catch (IllegalArgumentException exception){
                output.append("Invalid Customer\n");
            }
        }


        return output.toString();
    }





    private Customer convertCustomerJson(CustomerDtoInputJson customerJson){

        Customer customer = new Customer();
        customer.setTown(this.findTown(customerJson.getTown().getName()));
        customer.setEmail(customerJson.getEmail());
        customer.setFirstName(customerJson.getFirstName());
        customer.setLastName(customerJson.getLastName());

        String[] splitData = customerJson.getRegisteredOn().split("/");

        int day = Integer.parseInt(splitData[0]);
        int month = Integer.parseInt(splitData[1]);
        int year = Integer.parseInt(splitData[2]);

        LocalDate date = LocalDate.of(year,month,day);
        customer.setRegisteredOn(date);

        return customer;
    }
    private Town findTown(String name){
        Town town = this.townRepository.findTownByName(name);

        if (town == null){
            throw new IllegalArgumentException();
        }

        return town;
    }
}
