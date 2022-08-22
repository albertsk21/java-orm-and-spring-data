package person.core;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import person.services.PeopleService;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private PeopleService peopleService;

    public ConsoleRunner(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public void run(String... args) throws Exception {

        this.peopleService.registerPeople();
    }
}
