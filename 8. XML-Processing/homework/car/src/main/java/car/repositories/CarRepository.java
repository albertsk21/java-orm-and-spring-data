package car.repositories;

import car.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.event.ListDataEvent;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query("FROM Car WHERE id = ?1 ")
    Car finCarById(Long id);

    @Query("FROM Car WHERE make = 'Toyota' "+
           "ORDER BY model, travelledDistance DESC")
    Car[] findAllCarsMakeByToyota();


    @Query("SELECT id FROM Car")
    List<Long> getAllIdes();

    @Query("FROM Car")
    Car[] getAllCars();



}
