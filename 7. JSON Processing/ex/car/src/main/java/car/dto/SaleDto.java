package car.dto;

import car.models.Car;
import car.models.Customer;



public class SaleDto {

    private Long id;
    private float discount;
    private CarDto carDto;
    private CustomerDto customerDto;

    public SaleDto() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public void setCarDto(CarDto carDto) {
        this.carDto = carDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public Long getId() {
        return id;
    }

    public float getDiscount() {
        return discount;
    }

    public CarDto getCarDto() {
        return carDto;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }
}
