package softuni.workshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.workshop.data.entities.Project;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {


    @Query("FROM Project p WHERE p.name = ?1")
    Optional<Project> findByName(String name);


    @Query("FROM Project u WHERE u.finished = true " +
           "ORDER BY u.name DESC ")
    List<Project> findProjectsWhichAreFinished();

}
