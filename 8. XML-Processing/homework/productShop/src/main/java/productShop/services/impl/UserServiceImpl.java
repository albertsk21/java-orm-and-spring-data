package productShop.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import productShop.dto.json.UserJsonDto;
import productShop.dto.xml.input.UserXmlDto;
import productShop.dto.xml.input.UsersRootDto;
import productShop.dto.xml.output.query2.ProductXmlDto;
import productShop.dto.xml.output.query2.SoldProductRootXml;
import productShop.dto.xml.output.query2.UserRootXmlDto;
import productShop.dto.xml.output.query2.UserXmlOutput;
import productShop.models.Product;
import productShop.models.User;
import productShop.paths.input.InputPaths;
import productShop.paths.output.OutputPaths;
import productShop.repositories.UserRepository;
import productShop.services.interfaces.UserService;
import productShop.util.interfaces.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper modelMapper, XmlParser xmlParser) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
    }

    private String readFileFromJson() throws IOException {
        return Files.readString(Path.of(InputPaths.USERS_FILE_JSON_PATH));
    }

    @Override
    public void registerFromJson() throws IOException {

        UserJsonDto[] usersDto = this.gson.fromJson(this.readFileFromJson(),UserJsonDto[].class);
        User[] users = this.modelMapper.map(usersDto,User[].class);
        for (User user : users) {
            this.userRepository.saveAndFlush(user);
        }

    }

    @Override
    public void registerFromXml() throws JAXBException {

        UsersRootDto usersRootDto = this.xmlParser.parseXml(UsersRootDto.class,InputPaths.USERS_FILE_XML_PATH);
        for ( UserXmlDto userXmlDto : usersRootDto.getUsers()) {
            User user = this.modelMapper.map(userXmlDto,User.class);
            this.userRepository.saveAndFlush(user);

        }
    }


    @Override
    public void insertDataInUsersSoldProductXml() throws JAXBException, FileNotFoundException {
        UserRootXmlDto userRootXmlDto = this.convertUsersInUserRootXmlDto(this.userRepository.getAllUsers());


        this.xmlParser.parseOutput(userRootXmlDto, OutputPaths.USER_SOLD_PRODUCTS_XML_PATH);

     }


    public UserRootXmlDto convertUsersInUserRootXmlDto(User[] users){


        UserRootXmlDto userRootXmlDto =  new UserRootXmlDto();

        for (User user : users) {

            UserXmlOutput userXmlOutput = new UserXmlOutput();
            userXmlOutput.setFirstName(user.getFirstName());
            userXmlOutput.setLastName(user.getLastName());

            SoldProductRootXml soldProductRootXml = new SoldProductRootXml();


            for ( Product product : user.getProductsBuyer()) {

                ProductXmlDto productXmlDto = new ProductXmlDto();
                productXmlDto.setName(product.getName());
                productXmlDto.setPrice(product.getPrice());
                productXmlDto.setBuyerFirstName(product.getBuyer().getFirstName());
                productXmlDto.setBuyerLastName(product.getBuyer().getLastName());

                soldProductRootXml.getProducts().add(productXmlDto);

            }

            userXmlOutput.setSoldProductRootXml(soldProductRootXml);


            userRootXmlDto.getUsers().add(userXmlOutput);

        }
        return userRootXmlDto;
    }





}
