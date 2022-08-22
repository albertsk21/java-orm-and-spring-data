package car.services.implementation;

import car.dto.SupplierDto;
import car.models.Supplier;
import car.pojo.SupplierPojo;
import car.repositories.SupplierRepository;
import car.services.interfaces.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    private final String SUPPLIER_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\input\\suppliers.json";
    private final String  LOCAL_SUPPLIERS_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\car\\src\\main\\resources\\static\\files\\json\\output\\local-suppliers.json";
    public SupplierServiceImpl(SupplierRepository supplierRepository, Gson gson, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;

        this.gson = gson;
        this.modelMapper = modelMapper;
    }


    private String readFileFromJson() throws IOException {
        return Files.readString(Path.of(SUPPLIER_PATH));
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
        FileWriter writerJson = new FileWriter(LOCAL_SUPPLIERS_PATH);
        writerJson.write(content);
        writerJson.close();



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
