package exam.service.impl;

import com.google.gson.Gson;
import exam.config.interfaces.XmlParser;
import exam.data.entity.xml.ShopDtoInputXml;
import exam.data.entity.xml.ShopRootInputXml;
import exam.data.model.Shop;
import exam.data.model.Town;
import exam.path.PathsProject;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class  ShopServiceImpl implements ShopService {

    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private TownRepository townRepository;
    private ShopRepository shopRepository;
    private Gson gson;


    public ShopServiceImpl(ModelMapper modelMapper, XmlParser xmlParser, TownRepository townRepository, ShopRepository shopRepository, Gson gson) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.townRepository = townRepository;
        this.shopRepository = shopRepository;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(PathsProject.SHOPS_XML));
    }

    @Override
    public String importShops() throws JAXBException{
        ShopRootInputXml shopsXml = this.xmlParser.parseXml(ShopRootInputXml.class,PathsProject.SHOPS_XML);
        StringBuilder output = new StringBuilder();

        for (ShopDtoInputXml shopXml : shopsXml.getShops()) {
            try {
                Shop shop = this.convertShopXml(shopXml);
                this.shopRepository.saveAndFlush(shop);
                output.append(String.format("Successfully imported Shop %s - %d \n",shop.getName(),shop.getShopArea()));

            }catch (IllegalArgumentException exception){
                output.append("Invalid shop\n");
            }

        }
        return output.toString();
    }






    private Shop convertShopXml(ShopDtoInputXml shopXml){

        Shop shop = new Shop();

        shop.setName(shopXml.getName());
        shop.setShopArea(shopXml.getShopArea());
        shop.setAddress(shopXml.getAddress());
        shop.setTown(this.getTownByName(shopXml.getTown().getName()));
        shop.setEmployeeCount(shopXml.getEmployeeCount());
        this.throwExceptionIfExistShop(shopXml.getName());
        return shop;

    }

    private Town getTownByName(String  name){

        Town town = this.townRepository.findTownByName(name);

        if (town == null){
            throw new IllegalArgumentException();
        }


        return town;
    }

    private void throwExceptionIfExistShop(String nameShop){
        Shop shop = this.shopRepository.finByName(nameShop);
        if(shop != null){
            throw new IllegalArgumentException();
        }

    }

}
