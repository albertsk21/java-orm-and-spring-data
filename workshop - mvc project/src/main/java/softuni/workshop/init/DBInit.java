package softuni.workshop.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.workshop.service.services.UserService;

@Component
public class DBInit implements CommandLineRunner {

    private UserService userService;

    public DBInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args){
        this.userService.initUsers();
    }
}
