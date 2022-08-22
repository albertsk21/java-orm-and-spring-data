package car.services.implementation;


import car.dto.json.PartDto;
import car.dto.json.SupplierDto;
import car.dto.xml.input.PartRootXmlInput;
import car.dto.xml.input.PartXmlDtoInput;
import car.models.Part;
import car.models.Supplier;
import car.paths.input.Paths;
import car.repositories.PartRepository;
import car.repositories.SupplierRepository;
import car.services.interfaces.PartService;
import car.util.interfaces.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
public class PartServiceImpl implements PartService {
    private PartRepository partRepository;
    private SupplierRepository supplierRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }



    @Override
    public void importAllPartsFromJson() throws IOException {

        List<PartDto> partDtoListFromJson = this.getPartDtoListFromJson();
        this.sentPartsToDataBase(partDtoListFromJson);
    }
    @Override
    public void importAllPartsFromXml() throws JAXBException {
        PartRootXmlInput partRootXmlInput = this.xmlParser.parseXml(PartRootXmlInput.class, Paths.PARTS_PATH_XML);


        for (PartXmlDtoInput partXmlDtoInput : partRootXmlInput.getParts() ) {

            Part part = this.modelMapper.map(partXmlDtoInput, Part.class );
            Supplier randomSupplier = this.getRandomSupplier();
            part.setSupplier(randomSupplier);
            this.partRepository.saveAndFlush(part);
        }

    }


    private String readFileFromParts() throws IOException {
        return Files.readString(Path.of(Paths.PARTS_PATH_JSON));
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
    private Supplier getRandomSupplier(){
        Random random = new Random();
        List<Long> validIdes = this.supplierRepository.getAllIdWhereIsImporter();
        Long randomId = validIdes.get(random.nextInt(validIdes.size()));
        Supplier supplier =  this.supplierRepository.findSupplierById(randomId);
        return supplier;
    }
    private void sentPartsToDataBase(List<PartDto> partsDto){


        Part[] parts = this.modelMapper.map(partsDto, Part[].class);
        for (Part part : parts) {
            this.partRepository.save(part);
        }


    }
}
