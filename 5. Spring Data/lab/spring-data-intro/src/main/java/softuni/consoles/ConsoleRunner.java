package softuni.consoles;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.entities.Account;
import softuni.entities.User;
import softuni.services.interfaces.AccountService;
import softuni.services.interfaces.UserService;

import java.math.BigDecimal;

@Component
public class ConsoleRunner  implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;


    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User("Peter",20);
        Account account = new Account(new BigDecimal(25000));
        user.addAccount(account);

        userService.registerUser(user);


        this.accountService.withdrawMoney(new BigDecimal(20000),account.getId());
        this.accountService.transferMoney(new BigDecimal(20000),account.getId());
    }
}
