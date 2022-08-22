import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni_hibernate_intro");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        String all = "FROM Address";
        String townName = "Sofia";




     Query   query = em.createQuery(all);

        List<Address> addresses = query.getResultList();


        for (Address address : addresses) {

            if(address.getTown().getName().equals(townName)){

            }


        }












        em.getTransaction().commit();
;
    }

}

