package car.services.implementation;


import car.dto.PartDto;
import car.dto.SupplierDto;
import car.models.Part;
import car.models.Supplier;
import car.repositories.PartRepository;
import car.repositories.SupplierRepository;
import car.services.interfaces.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class PartServiceImpl implements PartService {
    private PartRepository partRepository;
    private SupplierRepository supplierRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final String PARTS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\input\\parts.json";

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    private String readFileFromParts() throws IOException {
        return Files.readString(Path.of(PARTS_PATH));
    }

    @Override
    public void importAllPartsFromJson() throws IOException {

        List<PartDto> partDtoListFromJson = this.getPartDtoListFromJson();
        this.sentPartsToDataBase(partDtoListFromJson);
    }

    private List<PartDto> getPartDtoListFromJson() throws IOException {
        PartDto[] partDtoArray = this.gson.fromJson(this.readFileFromParts(),PartDto[].class);
        List<PartDto> partDtoList =  new ArrayList<>();
        for ( PartDto partDto : partDtoArray){
            SupplierDto randomSupplier = this.getRandomSupplierDto();
            partDto.setSupplierDto(randomSupplier);
            partDtoList.add(partDto);
        }

        return partDtoList;
    }
    private SupplierDto getRandomSupplierDto(){
        Random random = new Random();
        List<Long> validIdes = this.supplierRepository.getAllIdWhereIsImporter();
        Long randomId = validIdes.get(random.nextInt(validIdes.size()));
        Supplier supplier =  this.supplierRepository.findSupplierById(randomId);
        return this.modelMapper.map(supplier,SupplierDto.class);
    }
    private void sentPartsToDataBase(List<PartDto> partsDto){


        Part[] parts = this.modelMapper.map(partsDto, Part[].class);
        for (Part part : parts) {
            this.partRepository.save(part);
        }


    }
}
