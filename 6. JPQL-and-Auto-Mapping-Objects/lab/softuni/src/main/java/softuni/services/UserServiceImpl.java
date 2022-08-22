package softuni.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.contraints.Errors;
import softuni.contraints.Output;
import softuni.entities.User;
import softuni.entities.UserDTO;
import softuni.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean findUserWithUsername(String username) {

        for (UserDTO userDTO : this.userDTOList()) {
            if (userDTO.getUsername().equals(username)){
                return true;
            }
        }

        return false;
    }



    @Override
    public void writeCommand() throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

        UserDTO loggedUser = null;
        label:
        while (true){

            String[] tokens = bfr.readLine().split("\\|");

            try {
                switch (tokens[0]) {
                    case "RegisterUser":
                        System.out.println(this.register(tokens));

                        break;
                    case "LoginUser":

                        loggedUser = this.login(tokens[1], tokens[2]);
                        System.out.printf(Output.SUCCESSFULLY_LOGGED, loggedUser.getUsername());

                        break;
                    case "Logout":

                        String username = "";
                        if (loggedUser != null) {
                            username = loggedUser.getUsername();
                        }
                        loggedUser = this.logout(loggedUser);
                        System.out.println(String.format(Output.SUCCESSFULLY_LOGOUT, username));
                        break;
                    case "Leave":

                        break label;
                }
            }catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }



        }

    }


    private User convertUserDto(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO,User.class);
    }
    private String register(String[] tokens){


        String email = tokens[1];

        if(this.findUserByEmail(email)){
            throw new IllegalArgumentException(Errors.EXIST_EMAIL);
        }

        String password = tokens[2];
        String confirmPassword = tokens[3];
        String fullName = tokens[4];
        String username = tokens[5];

        if(this.findUserByUsername(username)){
            throw new IllegalArgumentException(Errors.EXIST_USERNAME);
        }
        UserDTO userDTO = new UserDTO(username,fullName,email,password,confirmPassword);
        User entityUser = this.convertUserDto(userDTO);
        this.userRepository.saveAndFlush(entityUser);



        return String.format(Output.SUCCESSFULLY_REGISTERED,fullName);
    }
    private UserDTO login(String email,String password){
        return this.findUser(email,password);
    }
    private UserDTO logout(UserDTO userDTO){
        ModelMapper modelMapper = new ModelMapper();

        if(userDTO == null){
            throw new IllegalArgumentException(Errors.CANNOT_LOGOUT);
        }

        userDTO.modifiedDate();
        User userSaved = modelMapper.map(userDTO,User.class);

        this.userRepository.save(userSaved);
        return null;
    }

    
    
    private UserDTO findUser(String email,String password){


        for (UserDTO userDTO : this.userDTOList()) {
            if(userDTO.getEmail().equals(email) && userDTO.getPassword().equals(password)){
                return userDTO;
            }
        }

        throw new IllegalArgumentException(Errors.INCORRECT_EMAIL_PASSWORD);
    }
    private boolean findUserByEmail(String email){


        for (UserDTO userDTO : userDTOList()) {

            if(userDTO.getEmail().equals(email)){
                return true;
            }
        }

        return false;
    }
    private boolean findUserByUsername(String username){


        for (UserDTO userDTO : userDTOList()) {
            if(userDTO.getUsername().equals(username)){
                return true;
            }
        }

        return false;
    }
    private List<UserDTO> userDTOList() {
        List<UserDTO> userDTOList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (User user : this.userRepository.findAll()) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOList.add(userDTO);

        }


        return userDTOList;
    }
}
