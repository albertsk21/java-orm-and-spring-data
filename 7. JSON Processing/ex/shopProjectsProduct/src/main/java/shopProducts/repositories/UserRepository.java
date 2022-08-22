package shopProducts.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shopProducts.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query("FROM User WHERE id = ?1")
    User findUserById(Long id);



    @Query("FROM User AS u "+
           "JOIN u.productsBuyer AS p "+
           "GROUP BY u.id " +
           "HAVING COUNT(p.id) >= 1 "+
           "ORDER BY u.firstName , u.lastName")
    List<User> getAllUsersWithLeastItemSold();
}
