package com.example.demo.services.implemenation;


import com.example.demo.entities.entitydatabases.Address;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.services.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public void save(Address address) {
        this.addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
       return this.addressRepository.findAddressById(id);
    }
}
