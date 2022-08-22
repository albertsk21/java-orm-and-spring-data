import org.hibernate.Query;
import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Main {

    public static void main(String[] args){

        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Town.class);

        SessionFactory sf = config.buildSessionFactory();

        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();







        tx.commit();


    }
}
