package softuni.services;

import org.springframework.stereotype.Service;
import softuni.entities.Account;
import softuni.repositories.AccountRepository;
import softuni.services.interfaces.AccountService;

import java.math.BigDecimal;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {

        Account account = accountRepository.findAccountById(id);


        if( account != null){

            if(amount.compareTo((account.getBalance())) >0){
                account.setBalance(account.getBalance().divide(amount));
                System.out.println("Transaction successfully!");
            }

        }



    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {

        Account account = accountRepository.findAccountById(id);

        if(account != null){
            boolean isNotNegativeNumber = amount.compareTo(new BigDecimal(0)) > 0;
            if(isNotNegativeNumber){
                account.setBalance(account.getBalance().add(amount));
            }

        }


    }
}
