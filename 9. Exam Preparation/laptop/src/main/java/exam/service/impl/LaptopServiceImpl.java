package exam.service.impl;

import com.google.gson.Gson;
import exam.config.interfaces.XmlParser;
import exam.data.entity.json.LaptopDtoInputJson;
import exam.data.model.Laptop;
import exam.data.model.Shop;
import exam.enums.WarrantyType;
import exam.path.PathsProject;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class LaptopServiceImpl implements LaptopService {
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private LaptopRepository laptopRepository;
    private ShopRepository shopRepository;
    private Gson gson;

    public LaptopServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, LaptopRepository laptopRepository, ShopRepository shopRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(PathsProject.LAPTOPS_JSON));
    }

    @Override
    public String importLaptops() throws IOException {

        LaptopDtoInputJson[] laptopsJson = this.gson.fromJson(this.readLaptopsFileContent(),LaptopDtoInputJson[].class);


        StringBuilder output = new StringBuilder();
        for (LaptopDtoInputJson laptopJson : laptopsJson) {
            try{
                Laptop laptop = this.convertLaptopJson(laptopJson);
                this.laptopRepository.saveAndFlush(laptop);
                output.append(String.format("Successfully imported Laptop %s - %.2f - %d - %d\n",laptop.getMacAddress(),laptop.getCpuSpeed(),laptop.getRam(),laptop.getStorage()));
            }catch (IllegalArgumentException exception){
                output.append("invalid Laptop\n");
            }

        }

        return output.toString();
    }



    private Laptop convertLaptopJson(LaptopDtoInputJson laptopJson){

        Laptop laptop = new Laptop();

        laptop.setCpuSpeed(laptopJson.getCpuSpeed());
        laptop.setDescription(laptopJson.getDescription());
        laptop.setPrice(laptopJson.getPrice());
        laptop.setWarrantyType(this.findWarrantyType(laptopJson.getWarrantyType()));
        laptop.setStorage(laptopJson.getStorage());
        laptop.setMacAddress(laptopJson.getMacAddress());
        laptop.setShop(this.findShopByName(laptopJson.getShop().getName()));
        laptop.setRam(laptopJson.getRam());


        return laptop;
    }

    private WarrantyType findWarrantyType(String n){
        return switch (n) {
            case "BASIC" -> WarrantyType.BASIC;
            case "PREMIUM" -> WarrantyType.PREMIUM;
            case "LIFETIME" -> WarrantyType.LIFETIME;
            default -> throw new IllegalArgumentException();
        };
    }



    private Shop findShopByName(String name){

        Shop shop  = this.shopRepository.finByName(name);

        if (shop == null){
            throw new IllegalArgumentException();
        }

        return shop;

    }
    @Override
    public String exportBestLaptops() {

        StringBuilder output = new StringBuilder();


        String formatLaptop = "Laptop - %s\n" +
                "*Cpu speed - %.2f\n" +
                "**Ram - %d\n" +
                "***Storage - %d\n" +
                "****Price - %.2f\n" +
                "#Shop name - %s\n" +
                "##Town - %s\n";

        for ( Laptop laptop : this.laptopRepository.getAllLaptop() ) {


            output.append(String.format(formatLaptop,
                    laptop.getMacAddress(),
                    laptop.getCpuSpeed(),
                    laptop.getRam(),
                    laptop.getStorage(),
                    laptop.getPrice(),
                    laptop.getShop().getName(),
                    laptop.getShop().getTown().getName()));

        }




        return output.toString();
    }
}
