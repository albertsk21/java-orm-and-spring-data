import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String townName = "Sofia";
        String selectEmployeesWhereLiveInTown = "SELECT e FROM Employee AS e " +
                                                "JOIN e.address AS a " +
                                                "JOIN a.town AS t " +
                                                "WHERE t.name = :town";
        String selectAddressesByTownName = "SELECT d FROM Address AS d " +
                                       "JOIN d.town AS t " +
                                       "WHERE t.name = :town";
        String getTownByName = "FROM Town WHERE name = :town";
        Query query;


        entityManager.getTransaction().begin();
        query = entityManager.createQuery(selectEmployeesWhereLiveInTown).setParameter("town",townName);
        List<Employee> employees = query.getResultList();
        for (Employee employee : employees){
            employee.setAddress(null);
        }


//        ---- SELECT ADDRESS BY TOWN
        query = entityManager.createQuery(selectAddressesByTownName).setParameter("town",townName);
        List<Address> addresses = query.getResultList();

        for (Address address :  addresses){
            entityManager.remove(address);
        }
//        ---- SELECT TOWN BY NAME
        query = entityManager.createQuery(getTownByName).setParameter("town",townName);
        Town town = (Town) query.getSingleResult();
        entityManager.remove(town);


        entityManager.getTransaction().commit();
        System.out.printf("%d address in %s deleted%n",addresses.size(),townName);
    }
}
