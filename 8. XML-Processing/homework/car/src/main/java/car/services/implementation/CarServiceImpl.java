package car.services.implementation;

import car.dto.json.CarDto;
import car.dto.json.PartDto;
import car.dto.xml.input.CarInputXml;
import car.dto.xml.input.CarRootXmlInput;
import car.dto.xml.output.query2.CarXmlOutputQuery2;
import car.dto.xml.output.query2.CarsRootXmlOutputQuery2;
import car.dto.xml.output.query4.CarRootXmlOutputQuery4;
import car.dto.xml.output.query4.CarXmlOutputQuery4;
import car.dto.xml.output.query4.PartRootXmlOutputQuery4;
import car.dto.xml.output.query4.PartXmlOutputQuery4;
import car.models.Car;
import car.models.Part;
import car.paths.input.Paths;
import car.pojo.CarPojo;
import car.repositories.CarRepository;
import car.repositories.PartRepository;
import car.services.interfaces.CarService;
import car.util.interfaces.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
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
    private XmlParser xmlParser;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }




    @Override
    public void ImportCarsFromJson() throws IOException {
        CarDto[] carsDtoArray = this.gson.fromJson(this.readFilesCarFromJson(),CarDto[].class);
        for (CarDto carDto : carsDtoArray) {

            PartDto partDto = this.getRandomPartDtoBetween10And20();
            carDto.getParts().add(partDto);


        }


        this.importCarsToDataBase(carsDtoArray);




    }
    @Override
    public void getAllCarsMakeByToyota() throws IOException {
        CarDto[] carsDto = this.getAllCarsDtoMakeByToyota();
        CarPojo[] carsPojo = this.convertCarsDtoInCarsPojo(carsDto);
        String content = this.gson.toJson(carsPojo);
        FileWriter writeJson = new FileWriter(Paths.TOYOTA_CARS_PATH_JSON);
        writeJson.write(content);
        writeJson.close();
    }
    @Override
    public void exportAllCarsWithParts() throws IOException {
        CarDto[] carsDto = this.getAllCarsDto();
        CarPojo[] carsPojo = this.modelMapper.map(carsDto,CarPojo[].class);
        String content = this.gson.toJson(carsPojo);
        FileWriter writerJson = new FileWriter(Paths.CARS_AND_PARTS_PATH_JSON);


        writerJson.write(content);
        writerJson.close();




    }
    @Override
    public void ImportCarsFromXml() throws JAXBException {
        CarRootXmlInput carRootXmlInput = this.xmlParser.parseXml(CarRootXmlInput.class, Paths.CARS_PATH_XML);

        for (CarInputXml carInputXml : carRootXmlInput.getCars()) {
            Car car = this.modelMapper.map(carInputXml,Car.class);
            Part randomPart = this.getRandomPartBetween10And20();
            car.getParts().add(randomPart);

            this.carRepository.saveAndFlush(car);
        }




    }


    @Override
    public void getAllCarsMakeByToyotaXml() throws JAXBException, FileNotFoundException {

        CarsRootXmlOutputQuery2 carsRootXmlOutputQuery2 = new CarsRootXmlOutputQuery2();


        for (Car car : this.carRepository.findAllCarsMakeByToyota() ) {
            CarXmlOutputQuery2 carXmlOutputQuery2 = this.modelMapper.map(car,CarXmlOutputQuery2.class);
            carsRootXmlOutputQuery2.getCars().add(carXmlOutputQuery2);
        }

        this.xmlParser.parseOutput(carsRootXmlOutputQuery2,Paths.TOYOTA_CARS_PATH_XML);
    }


    @Override
    public void exportAllCarsWithPartsXml() throws JAXBException, FileNotFoundException {


        CarRootXmlOutputQuery4 carsXml = new CarRootXmlOutputQuery4();


        for (Car car : this.carRepository.getAllCars()) {

            CarXmlOutputQuery4 carXml = new CarXmlOutputQuery4();
            carXml.setId(car.getId());
            carXml.setMake(car.getMake());
            carXml.setModel(car.getModel());
            carXml.setTravelledDistance(car.getTravelledDistance());


            PartRootXmlOutputQuery4 partsXml = new PartRootXmlOutputQuery4();


            for ( Part part : car.getParts()) {

                PartXmlOutputQuery4 partXml = new PartXmlOutputQuery4();
                partXml.setName(part.getName());
                partXml.setPrice(part.getPrice());

                partsXml.getParts().add(partXml);

            }

            carXml.setParts(partsXml);



            carsXml.getCars().add(carXml);

        }



        this.xmlParser.parseOutput(carsXml,Paths.CARS_AND_PARTS_PATH_XML);

    }





    public CarDto[] getAllCarsDto(){
        Car[] cars = this.carRepository.getAllCars();
        CarDto[] carsDto = this.modelMapper.map(cars,CarDto[].class);
        return carsDto;

    }
    private String readFilesCarFromJson() throws IOException {
        return Files.readString(Path.of(Paths.CAR_PATH_JSON));
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
    private PartDto getRandomPartDtoBetween10And20(){
        List<Long> partIds = this.partRepository.getRandomIdBetween10And20();
        Random random = new Random();
        Long randomId  = partIds.get(random.nextInt(partIds.size()));
        return this.modelMapper.map(this.partRepository.findPartById(randomId),PartDto.class);
    }
    private Part getRandomPartBetween10And20(){
        List<Long> partIds = this.partRepository.getRandomIdBetween10And20();
        Random random = new Random();
        Long randomId  = partIds.get(random.nextInt(partIds.size()));
        return this.partRepository.findPartById(randomId);
    }
    private void importCarsToDataBase(CarDto[] carsDtoArray) {

        Car[] cars = this.modelMapper.map(carsDtoArray,Car[].class);


        for (Car car: cars) {
            this.carRepository.save(car);
        }


    }
}
