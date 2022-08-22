package exam.repository;


import exam.data.model.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TownRepository extends JpaRepository<Town,Long> {


    @Query("FROM Town WHERE name = ?1")
    Town findTownByName(String name);
}
