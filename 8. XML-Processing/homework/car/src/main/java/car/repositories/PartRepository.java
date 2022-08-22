package car.repositories;

import car.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PartRepository extends JpaRepository<Part,Long> {


    @Query("SELECT id FROM Part WHERE id BETWEEN 10 AND 20")
    List<Long> getRandomIdBetween10And20();


    @Query("FROM Part WHERE id = ?1")
    Part findPartById(Long id);
}
