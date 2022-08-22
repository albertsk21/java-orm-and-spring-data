package com.example.demo.entities.entitydatabases;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String city;
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Employee> employees;

    public Address(String city) {
        this.city = city;
        this.employees = new LinkedHashSet<>();
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
