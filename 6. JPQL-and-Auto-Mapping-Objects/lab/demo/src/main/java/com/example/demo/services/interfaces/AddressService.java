package com.example.demo.services.interfaces;


import com.example.demo.entities.entitydatabases.Address;

import java.util.List;

public interface AddressService{

    void save(Address  address);
    List<Address> getAll();
    Address findById(Long id);

}
