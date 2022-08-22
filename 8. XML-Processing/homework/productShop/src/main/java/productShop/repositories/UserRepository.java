package productShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import productShop.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT COUNT(u.id) FROM User AS u ")
    int getLengthUser();

    @Query("FROM User WHERE id = ?1")
    User getUserById(Long id);


    @Query("FROM User")
    User[] getAllUsers();




}
