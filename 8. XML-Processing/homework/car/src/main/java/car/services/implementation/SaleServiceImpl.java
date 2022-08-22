package car.services.implementation;

import car.dto.json.CarDto;
import car.dto.json.CustomerDto;
import car.dto.json.PartDto;
import car.dto.json.SaleDto;
import car.dto.xml.output.query6.CarXmlOutputQuery6;
import car.dto.xml.output.query6.SaleXmlOutputQuery6;
import car.dto.xml.output.query6.SalesRootXmlOutputQuery6;
import car.models.Car;
import car.models.Customer;
import car.models.Part;
import car.models.Sale;
import car.paths.input.Paths;
import car.pojo.CarPojo;
import car.pojo.PartPojo;
import car.pojo.SalePojo;
import car.repositories.CarRepository;
import car.repositories.CustomerRepository;
import car.repositories.SaleRepository;
import car.services.interfaces.SaleService;
import car.util.interfaces.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SaleServiceImpl implements SaleService {
        private SaleRepository saleRepository;
        private CustomerRepository customerRepository;
        private CarRepository carRepository;
        private ModelMapper modelMapper;
        private Gson gson;
        private XmlParser xmlParser;


    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository, ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }

    @Override
    public void insertRandomSales(){


        Sale[] sales = this.generateRandomSales();
        this.importSaleToDataBase(sales);
        System.out.println();
    }
    @Override
    public void getInformationAboutSales(){
        SaleDto[] salesDto = this.getAllSalesDto();
        SalePojo[] salesPojo = this.convertSalesDtoInPojo(salesDto);
    }
    @Override
    public void getInformationAboutSalesXml() throws JAXBException, FileNotFoundException {
        SaleDto[] salesDto = this.getAllSalesDto();
        SalePojo[] salesPojo = this.convertSalesDtoInPojo(salesDto);


        SalesRootXmlOutputQuery6 salesXml = new SalesRootXmlOutputQuery6();

        for (SalePojo salePojo : salesPojo) {


            SaleXmlOutputQuery6 saleXml = new SaleXmlOutputQuery6();
            CarXmlOutputQuery6 carXml = this.modelMapper.map(salePojo.getCar(),CarXmlOutputQuery6.class);
            saleXml.setCar(carXml);
            saleXml.setCustomerName(salePojo.getCustomerName());
            saleXml.setPrice(salePojo.getPrice());
            saleXml.setDiscount(salePojo.getDiscount());
            saleXml.setPriceWithDiscount(salePojo.getPriceWithDiscount());
            salesXml.getSales().add(saleXml);

        }




        this.xmlParser.parseOutput(salesXml, Paths.SALES_DISCOUNTS_PATH_XML);


    }





    private SaleDto[] getAllSalesDto(){
        Sale[] sales = this.saleRepository.getAllSales();
        return this.modelMapper.map(sales,SaleDto[].class);
    }
    private SalePojo[] convertSalesDtoInPojo(SaleDto[] salesDto){

        List<SalePojo> salesPojo = new ArrayList<>();

        for (Sale sale  : this.saleRepository.getAllSales()) {

            SalePojo salePojo = new SalePojo();

            if (sale.getCar() != null){


                CarPojo carPojo = this.convertCarInPojo(sale.getCar());
                salePojo.setCar(carPojo);
                salePojo.setCustomerName(sale.getCustomer().getName());
                salePojo.setDiscount(sale.getDiscount());


                double currentPrice = 0;
                for ( PartPojo partPojo  : salePojo.getCar().getParts()) {
                    currentPrice += Double.parseDouble(partPojo.getPrice().toString());
                }
                salePojo.setPrice(new BigDecimal(String.valueOf(currentPrice)));
                double priceWithDiscount = currentPrice - (salePojo.getDiscount() * currentPrice);
                salePojo.setPriceWithDiscount(new BigDecimal(String.valueOf(priceWithDiscount)));

                salesPojo.add(salePojo);

            }

        }

        return salesPojo.toArray(SalePojo[]::new);
    }



    private CarPojo convertCarInPojo(Car car ){

        CarPojo carPojo = new CarPojo();



            carPojo.setMake(car.getMake());


        carPojo.setModel(car.getModel());


        Set<PartPojo> partsPojo = new LinkedHashSet<>();
        for (Part part : car.getParts()) {

            PartPojo partPojo =  new PartPojo();
            partPojo.setName(part.getName());
            partPojo.setPrice(part.getPrice());
            partsPojo.add(partPojo);

        }


        carPojo.setParts(partsPojo);
        carPojo.setTravelledDistance(car.getTravelledDistance());


        return carPojo;
    }
    private Sale[] generateRandomSales(){
        List<Float> discounts = new ArrayList<>();
        discounts.add((float) 0);
        discounts.add((float) 0.05);
        discounts.add((float) 0.1);
        discounts.add((float) 0.15);
        discounts.add((float) 0.2);
        discounts.add((float) 0.3);
        discounts.add((float) 0.4);
        discounts.add((float) 0.4);



        Random random =  new Random();
        List<Sale> sales= new ArrayList<>();
        for (int i = 0; i <= 100 ; i++) {

            Customer randomCustomer = this.getRandomCustomer();
            Car randomCar = this.getRandomCar();
            int randomIndex = random.nextInt(discounts.size());


            Sale sale = new Sale();
            sale.setCar(randomCar);
            sale.setCustomer(randomCustomer);
            sale.setDiscount(discounts.get(randomIndex));
            sales.add(sale);
        }

        return sales.toArray(Sale[]::new);

    }
    private void importSaleDtoToDataBase(SaleDto[] salesDto){


        for (Sale sale : this.convertSalesDtoArrayToSales(salesDto)) {
            this.saleRepository.save(sale);
        }



    }
    private void importSaleToDataBase(Sale[] sales){


        for (Sale sale : sales) {
            this.saleRepository.save(sale);
        }



    }
    private Sale[] convertSalesDtoArrayToSales(SaleDto[] salesDto){
        return this.modelMapper.map(salesDto,Sale[].class);
    }
    private CustomerDto getRandomCustomerDto(){
        Random random = new Random();

        List<Long> idList = this.customerRepository.getAllId();
        int randomId = random.nextInt(idList.size());
        CustomerDto customerDto = this.modelMapper.map(this.customerRepository.findCustomerById(idList.get(randomId)), CustomerDto.class);


        return customerDto;

    }
    private Customer getRandomCustomer(){
        Random random = new Random();

        List<Long> idList = this.customerRepository.getAllId();
        int randomId = random.nextInt(idList.size());

        return this.customerRepository.findCustomerById(idList.get(randomId));

    }
    private CarDto getRandomCarDto(){
        Random random = new Random();
        List<Long> idList = this.carRepository.getAllIdes();
        int randomId = random.nextInt( idList.size());
        CarDto carDto = this.modelMapper.map(this.carRepository.finCarById(Long.parseLong(String.valueOf(randomId))),CarDto.class);


        return carDto;

    }
    private Car getRandomCar(){
        Random random = new Random();
        List<Long> idList = this.carRepository.getAllIdes();
        int randomId = random.nextInt( idList.size());

        return this.carRepository.finCarById(Long.parseLong(String.valueOf(randomId)));

    }

}
