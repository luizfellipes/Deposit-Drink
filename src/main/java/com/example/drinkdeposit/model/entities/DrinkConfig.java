package com.example.drinkdeposit.model.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_DRINK_CONFIG")
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id = 1;
    private Double MAX_ALCOHOLIC_CAPACITY = 500.0;
    private Double MAX_NONALCOHOLIC_CAPACITY = 400.0;
    private Set<String> PERMIT_SECTION = new HashSet<>(Arrays.asList("A", "B", "C", "D", "E"));

    public DrinkConfig() {
    }

    public DrinkConfig(Integer id, Double MAX_ALCOHOLIC_CAPACITY, Double MAX_NONALCOHOLIC_CAPACITY, Set<String> PERMIT_SECTION) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
        this.PERMIT_SECTION = PERMIT_SECTION;
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

    public Set<String> getPERMIT_SECTION() {
        return PERMIT_SECTION;
    }
}
