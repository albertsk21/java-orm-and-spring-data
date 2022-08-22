package car.services.implementation;

import car.dto.CarDto;
import car.dto.CustomerDto;
import car.dto.PartDto;
import car.dto.SaleDto;
import car.models.Sale;
import car.pojo.CarPojo;
import car.pojo.SalePojo;
import car.repositories.CarRepository;
import car.repositories.CustomerRepository;
import car.repositories.SaleRepository;
import car.services.interfaces.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
        private SaleRepository saleRepository;
        private CustomerRepository customerRepository;
        private CarRepository carRepository;
        private ModelMapper modelMapper;
        private Gson gson;


    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository, ModelMapper modelMapper, Gson gson) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }





    @Override
    public void insertRandomSales(){

        SaleDto[] salesDto = this.generateRandomSaleDto();
        this.importSaleDtoToDataBase(salesDto);
        System.out.println();
    }
    @Override
    public void getInformationAboutSales(){
        SaleDto[] salesDto = this.getAllSalesDto();
        SalePojo[] salesPojo = this.convertSalesDtoInPojo(salesDto);
    }


    private SaleDto[] getAllSalesDto(){
        Sale[] sales = this.saleRepository.getAllSales();
        return this.modelMapper.map(sales,SaleDto[].class);
    }
    private SalePojo[] convertSalesDtoInPojo(SaleDto[] salesDto){

        List<SalePojo> salesPojo = new ArrayList<>();

        for (SaleDto saleDto : salesDto) {

            SalePojo salePojo = new SalePojo();

            CarPojo carPojo = this.modelMapper.map(saleDto.getCarDto(),CarPojo.class);
            salePojo.setCar(carPojo);
            salePojo.setCustomerName(saleDto.getCustomerDto().getName());
            salePojo.setDiscount(saleDto.getDiscount());


            double currentPrice = 0;
            for ( PartDto partDto  : saleDto.getCarDto().getParts()) {
                 currentPrice += Double.parseDouble(partDto.getPrice().toString());
            }
            salePojo.setPrice(new BigDecimal(String.valueOf(currentPrice)));
            double priceWithDiscount = currentPrice - (salePojo.getDiscount() * currentPrice);
            salePojo.setPriceWithDiscount(new BigDecimal(String.valueOf(priceWithDiscount)));



            salesPojo.add(salePojo);

        }



        return salesPojo.toArray(SalePojo[]::new);
    }











    private SaleDto[] generateRandomSaleDto(){
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
        List<SaleDto > salesDto = new ArrayList<>();
        for (int i = 0; i <= 100 ; i++) {
            CustomerDto customerDto = this.getRandomCustomerDto();
            CarDto carDto = this.getRandomCarDto();
            int randomIndex = random.nextInt(discounts.size());


            SaleDto saleDto = new SaleDto();
            saleDto.setCarDto(carDto);
            saleDto.setCustomerDto(customerDto);
            saleDto.setDiscount(discounts.get(randomIndex));
            salesDto.add(saleDto);
        }




        return salesDto.toArray(SaleDto[]::new);

    }
    private void importSaleDtoToDataBase(SaleDto[] salesDto){


        for (Sale sale : this.convertSalesDtoArrayToSales(salesDto)) {
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
    private CarDto getRandomCarDto(){
        Random random = new Random();
        List<Long> idList = this.carRepository.getAllIdes();
        int randomId = random.nextInt( idList.size());
        CarDto carDto = this.modelMapper.map(this.carRepository.finCarById(Long.parseLong(String.valueOf(randomId))),CarDto.class);


        return carDto;

    }

}
