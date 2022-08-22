package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.modelDB.Player;
import softuni.exam.modelDB.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {


    @Query("FROM Team WHERE name = ?1 ")
    Team findByName(String name);



}
