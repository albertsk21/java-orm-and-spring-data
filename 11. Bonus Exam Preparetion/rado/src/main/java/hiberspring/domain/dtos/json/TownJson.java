package hiberspring.domain.dtos.json;


import com.google.gson.annotations.Expose;

import java.math.BigInteger;

public class TownJson {

    @Expose
    private String name;
    @Expose
    private BigInteger population;

    public TownJson() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPopulation(BigInteger population) {
        this.population = population;
    }

    public String getName() {
        return name;
    }
    public BigInteger getPopulation() {
        return population;
    }
}
