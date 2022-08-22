package softuni.workshop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import softuni.workshop.data.entities.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("FROM Project WHERE name = ?1")
    Project findProjectByName(String name);
}
