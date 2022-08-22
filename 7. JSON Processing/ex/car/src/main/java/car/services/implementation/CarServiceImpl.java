package car.services.implementation;

import car.dto.CarDto;
import car.dto.PartDto;
import car.models.Car;
import car.pojo.CarPojo;
import car.repositories.CarRepository;
import car.repositories.PartRepository;
import car.services.interfaces.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;


@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private PartRepository partRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final String CAR_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\input\\cars.json";
    private final String TOYOTA_CARS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\output\\toyota-cars.json";
    private final String CARS_AND_PARTS_PATH  = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\output\\cars-and-parts.json";

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }



    @Override
    public void ImportCarsFromJson() throws IOException {
        CarDto[] carsDtoArray = this.gson.fromJson(this.readFilesCarFromJson(),CarDto[].class);
        for (CarDto carDto : carsDtoArray) {

            PartDto partDto = this.getRandomPartBetween10And20();
            carDto.getParts().add(partDto);


        }


        this.importCarsToDataBase(carsDtoArray);




    }
    @Override
    public void getAllCarsMakeByToyota() throws IOException {
        CarDto[] carsDto = this.getAllCarsDtoMakeByToyota();
        CarPojo[] carsPojo = this.convertCarsDtoInCarsPojo(carsDto);
        String content = this.gson.toJson(carsPojo);
        FileWriter writeJson = new FileWriter(TOYOTA_CARS_PATH);
        writeJson.write(content);
        writeJson.close();
    }
    @Override
    public void exportAllCarsWithParts() throws IOException {
        CarDto[] carsDto = this.getAllCarsDto();
        CarPojo[] carsPojo = this.modelMapper.map(carsDto,CarPojo[].class);
        String content = this.gson.toJson(carsPojo);
        FileWriter writerJson = new FileWriter(CARS_AND_PARTS_PATH);


        writerJson.write(content);
        writerJson.close();




    }





    public CarDto[] getAllCarsDto(){
        Car[] cars = this.carRepository.getAllCars();
        CarDto[] carsDto = this.modelMapper.map(cars,CarDto[].class);
        return carsDto;

    }
    private String readFilesCarFromJson() throws IOException {
        return Files.readString(Path.of(CAR_PATH));
    }
    private CarPojo[] convertCarsDtoInCarsPojo(CarDto[] carsDto){
        return this.modelMapper.map(carsDto,CarPojo[].class);
    }
    private CarDto[]  getAllCarsDtoMakeByToyota(){
        Car[] cars = this.carRepository.findAllCarsMakeByToyota();
        return this.convertCarInCarDto(cars);
    }
    private CarDto[] convertCarInCarDto(Car[] cars){
        CarDto[] carsDto = this.modelMapper.map(cars,CarDto[].class);
        return carsDto;
    }
    private PartDto getRandomPartBetween10And20(){
        List<Long> partIds = this.partRepository.getRandomIdBetween10And20();
        Random random = new Random();
        Long randomId  = partIds.get(random.nextInt(partIds.size()));
        return this.modelMapper.map(this.partRepository.findPartById(randomId),PartDto.class);
    }
    private void importCarsToDataBase(CarDto[] carsDtoArray) {

        Car[] cars = this.modelMapper.map(carsDtoArray,Car[].class);


        for (Car car: cars) {
            this.carRepository.save(car);
        }


    }
}
