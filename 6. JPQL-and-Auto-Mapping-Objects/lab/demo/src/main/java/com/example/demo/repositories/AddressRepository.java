package com.example.demo.repositories;
import com.example.demo.entities.entitydatabases.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query("FROM Address WHERE id = ?1")
    Address findAddressById(Long id);
}
