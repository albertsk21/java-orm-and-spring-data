package softuni.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.services.UserService;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
    this.userService.writeCommand();
    }
}
