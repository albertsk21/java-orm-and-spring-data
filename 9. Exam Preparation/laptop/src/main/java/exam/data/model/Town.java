package exam.data.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int population;
    @Column(name = "travel_guide",columnDefinition="TEXT")
    private String travelGuide;
    @OneToMany(mappedBy = "town",cascade = CascadeType.ALL)
    private Set<Shop> shops = new LinkedHashSet<>();
    @OneToMany(mappedBy = "town", cascade = CascadeType.ALL)
    private Set<Customer> customers =  new LinkedHashSet<>();

    public Town() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!(name.length() >= 2)){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {

        if(population < 0){
            throw new IllegalArgumentException();
        }

        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        if(travelGuide.length() < 10){
            throw new IllegalArgumentException();
        }
        this.travelGuide = travelGuide;
    }

    public Set<Shop> getShops() {
        return shops;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}

