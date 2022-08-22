package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("From User WHERE username = :username")
    User getUserByUsername(String username);


    @Query("FROM User AS u " +
           "JOIN u.posts AS ps " +
           "JOIN u.posts.post AS p " +
           "ORDER BY u.username")
    User[] getAllUsers();

}
