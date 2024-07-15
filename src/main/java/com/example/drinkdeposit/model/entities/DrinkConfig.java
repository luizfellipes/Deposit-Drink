package com.example.drinkdeposit.model.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_DRINK_CONFIG")
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private final Double MAX_ALCOHOLIC_CAPACITY;
    private final Double MAX_NONALCOHOLIC_CAPACITY;
    private final String PERMIT_SECTION;

    public DrinkConfig() {
        this.MAX_ALCOHOLIC_CAPACITY = 500.0;
        this.MAX_NONALCOHOLIC_CAPACITY = 400.0;
        this.PERMIT_SECTION = Set.of("A", "B", "C", "D", "E").toString();
    }

    public DrinkConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, String permitSection) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = maxAlcoholicCapacity;
        this.MAX_NONALCOHOLIC_CAPACITY = maxNonAlcoholicCapacity;
        this.PERMIT_SECTION = permitSection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getMAX_ALCOHOLIC_CAPACITY() {
        return MAX_ALCOHOLIC_CAPACITY;
    }

    public Double getMAX_NONALCOHOLIC_CAPACITY() {
        return MAX_NONALCOHOLIC_CAPACITY;
    }

    public String getPERMIT_SECTION() {
        return PERMIT_SECTION;
    }
}
