package exam.repository;

import exam.data.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {


    @Query("FROM Shop WHERE name = ?1")
    Shop finByName(String name);
}
