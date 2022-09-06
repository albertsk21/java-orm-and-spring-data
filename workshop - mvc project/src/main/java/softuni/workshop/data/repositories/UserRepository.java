package softuni.workshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.workshop.data.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("FROM User u WHERE u.username = ?1")
    Optional<User> findByUsername(String username);
}
