package car.services.implementation;

import car.dto.json.SupplierDto;
import car.dto.xml.input.SupplierXmlDtoInput;
import car.dto.xml.input.SuppliersRootXmlInput;
import car.dto.xml.output.query3.SupplierXmlOutputQuery3;
import car.dto.xml.output.query3.SuppliersRootXmlOutputQuery3;
import car.models.Supplier;
import car.paths.input.Paths;
import car.pojo.SupplierPojo;
import car.repositories.SupplierRepository;
import car.services.interfaces.SupplierService;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private SupplierRepository supplierRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;


    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.supplierRepository = supplierRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }


    private String readFileFromJson() throws IOException {
        return Files.readString(Path.of(Paths.SUPPLIER_PATH));
    }

    @Override
    public void importAllSuppliersFromJson() throws IOException {

        SupplierDto[] suppliersDto =  this.gson.fromJson(this.readFileFromJson(),SupplierDto[].class);


        Supplier[] suppliers = this.modelMapper.map(suppliersDto,Supplier[].class);


        for (Supplier supplier :suppliers) {
            this.supplierRepository.save(supplier);
        }




    }

    @Override
    public void exportAllLocalSuppliers() throws IOException {
        SupplierDto[] suppliersDto = this.getSuppliersDtoIsImporterFromCountry();
        SupplierPojo[] suppliersPojo = this.convertSuppliersDtoToPojo(suppliersDto);

        String content = this.gson.toJson(suppliersPojo);
        FileWriter writerJson = new FileWriter(Paths.LOCAL_SUPPLIERS_PATH);
        writerJson.write(content);
        writerJson.close();



    }


    @Override
    public void importSuppliersFromXml() throws JAXBException {

        SuppliersRootXmlInput suppliersRootXml = this.xmlParser.parseXml(SuppliersRootXmlInput.class, Paths.SUPPLIERS_PATH_XML);


        for (SupplierXmlDtoInput supplierXmlDto : suppliersRootXml.getSuppliers() ) {


            Supplier supplierDB = this.modelMapper.map(supplierXmlDto,Supplier.class);

            this.supplierRepository.saveAndFlush(supplierDB);

        }

    }



    @Override
    public void exportAllLocalSuppliersXml() throws JAXBException, FileNotFoundException {

        SuppliersRootXmlOutputQuery3 suppliersXml = new SuppliersRootXmlOutputQuery3();

        for (Supplier supplier : this.supplierRepository.findSupplierWhoImportInCountry()) {


            SupplierXmlOutputQuery3 supplierXml = new SupplierXmlOutputQuery3();
            supplierXml.setId(supplier.getId());
            supplierXml.setName(supplier.getName());
            supplierXml.setPartsCount(supplier.getParts().size());


            suppliersXml.getSuppliers().add(supplierXml);
        }

        this.xmlParser.parseOutput(suppliersXml,Paths.LOCAL_SUPPLIERS_PATH_XML);

    }





    private SupplierDto[] getSuppliersDtoIsImporterFromCountry(){

        Supplier[] suppliers = this.supplierRepository.findSupplierWhoImportInCountry();
        SupplierDto[] suppliersDto = this.modelMapper.map(suppliers,SupplierDto[].class);
        return suppliersDto;


    }
    private SupplierPojo[] convertSuppliersDtoToPojo(SupplierDto[] suppliersDto){

        List<SupplierPojo> supplierPojoList = new ArrayList<>();

        for (SupplierDto supplierDto : suppliersDto) {

           SupplierPojo supplierPojo = new SupplierPojo();
           supplierPojo.setId(supplierDto.getId());
           supplierPojo.setName(supplierDto.getName());
           supplierPojo.setPartCount(supplierDto.getParts().size());


           supplierPojoList.add(supplierPojo);

        }

        return supplierPojoList.toArray(SupplierPojo[]::new);
    }

}
