package shampoo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shampoo.entities.database.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long> {
}
