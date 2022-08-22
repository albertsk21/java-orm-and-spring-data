import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String...args){


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("student");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student("ftest","ltest",12);

        em.persist(student);
        em.getTransaction().commit();

    }
}
