import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String...args) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {


        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine().trim();
        String password = scanner.nextLine().trim();
        String db = scanner.nextLine();

        Connector.createConnection(username, password, db);
        EntityManager<User> em = new EntityManager<>(Connector.getConnection());

        User testUserOne = new User("test_user1","1232434", 19, LocalDate.of(2016,5,2));
        User testUserTwo = new User("test_user2","1232434", 17, LocalDate.of(2019,1,1));
        User testUserThree = new User("test_user3","1232434" ,24, LocalDate.of(2021,11,2));
        em.persist(testUserOne);
        em.persist(testUserTwo);
        em.persist(testUserThree);
        User found = em.findFirst(User.class);
        System.out.printf("The first user: %s%n",found.getUsername());
        User firstUserUnder18  = em.findFirst(User.class," where age < 18");

        System.out.printf("The first user under 18 years old : %s%n",firstUserUnder18.getUsername());

    }
}

