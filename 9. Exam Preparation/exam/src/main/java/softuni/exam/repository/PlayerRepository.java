package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import softuni.exam.modelDB.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {



    @Query("FROM Player")
    Player[] findAllPlayers();





}
