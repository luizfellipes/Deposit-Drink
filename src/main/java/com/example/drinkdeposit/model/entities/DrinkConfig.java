package com.example.drinkdeposit.model.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_DRINK_CONFIG")
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id = 1;
    private int MAX_ALCOHOLIC_CAPACITY = 500;
    private int MAX_NONALCOHOLIC_CAPACITY = 400;

    public DrinkConfig() {
    }

    public DrinkConfig(Integer id, int MAX_ALCOHOLIC_CAPACITY, int MAX_NONALCOHOLIC_CAPACITY) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMAX_ALCOHOLIC_CAPACITY() {
        return MAX_ALCOHOLIC_CAPACITY;
    }

    public int getMAX_NONALCOHOLIC_CAPACITY() {
        return MAX_NONALCOHOLIC_CAPACITY;
    }

}
