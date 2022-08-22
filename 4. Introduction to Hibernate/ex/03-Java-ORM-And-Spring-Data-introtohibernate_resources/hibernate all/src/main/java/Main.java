import entities.*;
import org.hibernate.Query;
import  org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Locale;


public class  Main {
    public static  void  main(String...args){


        Configuration configuration =  new Configuration().configure()
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Town.class);


        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();


        Query query = session.createQuery("from Town");

        List<Town> towns =  query.list();


        for (Town town : towns) {


            System.out.println(town.getId() +" " +town.getName());


            if(town.getName().length() > 5){
                session.remove;
            }else{
                town.setName(town.getName().toLowerCase());
            }


        }


        tx.commit();


    }
}
