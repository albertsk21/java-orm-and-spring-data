package softuni.services;

import softuni.entities.User;
import softuni.repositories.UserRepository;
import softuni.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {

        if(userRepository.findUserByUsername(user.getUsername()) == null){


            System.out.println("successfully registered");
        }else{
            System.out.println("this name belongs to another person");
        }



    }
}
