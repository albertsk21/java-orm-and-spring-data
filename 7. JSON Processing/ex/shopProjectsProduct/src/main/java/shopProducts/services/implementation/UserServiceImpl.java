package shopProducts.services.implementation;


import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import shopProducts.dto.ProductDto;
import shopProducts.dto.UserDto;
import shopProducts.models.Product;
import shopProducts.models.User;
import shopProducts.repositories.UserRepository;
import shopProducts.services.interfaces.UserService;

import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private Gson gson;
    private ModelMapper modelMapper;
    private final String USER_PATH = "src/main/resources/static/files/json/users.json";
    private final String USER_SOLD_PATH = "C:\\Intelji projects\\Java ORM and Spring Data\\7. JSON Processing\\ex\\shopProjectsProduct\\src\\main\\resources\\static\\files\\json\\users-sold-products.json";


    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }



    private String readInformationFromFile() throws IOException {
        return Files.readString(Path.of(USER_PATH));
    }
    @Override
    public void registerUsers() throws IOException {

        UserDto[] usersDto = this.gson.fromJson(this.readInformationFromFile(),UserDto[].class);

        for (UserDto userDto : usersDto) {

            User user = this.modelMapper.map(userDto,User.class);
            this.userRepository.save(user);
        }

    }


    @Override
    public void getAllUserAtLeast1Sold() throws IOException {

        List<UserDto> usersDto = extractToUsersDto();

        String content = this.gson.toJson(usersDto);
        FileWriter fileWriter = new FileWriter(USER_SOLD_PATH);
        fileWriter.write(content);
        fileWriter.close();


    }
    private List<UserDto> extractToUsersDto(){



        Set<ProductDto> productsDto = new LinkedHashSet<>();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user :this.userRepository.getAllUsersWithLeastItemSold()) {




            for ( Product product : user.getProductsBuyer()) {
                ProductDto productDto = this.modelMapper.map(product,ProductDto.class);
                productsDto.add(productDto);
            }


            UserDto userDto = this.modelMapper.map(user,UserDto.class);
            userDto.setSoldProducts((Set<ProductDto>) productsDto);
            usersDto.add(userDto);
            productsDto = new LinkedHashSet<>();
        }




        return usersDto;

    }
}
