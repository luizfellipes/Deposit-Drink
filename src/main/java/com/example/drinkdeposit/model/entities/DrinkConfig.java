/*
package com.example.drinkdeposit.model.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;


@Configuration
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Value("${max.alcoholic.capacity}")
    private  String MAX_ALCOHOLIC_CAPACITY;
    @Value("${max.nonAlcoholic.capacity}")
    private  String MAX_NONALCOHOLIC_CAPACITY;


    public DrinkConfig() {
    }

    public DrinkConfig(String MAX_ALCOHOLIC_CAPACITY, String MAX_NONALCOHOLIC_CAPACITY) {
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
    }

    public DrinkConfig(Integer id, String MAX_ALCOHOLIC_CAPACITY, String MAX_NONALCOHOLIC_CAPACITY) {
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

    public String getMAX_ALCOHOLIC_CAPACITY() {
        return MAX_ALCOHOLIC_CAPACITY;
    }

    public String getMAX_NONALCOHOLIC_CAPACITY() {
        return MAX_NONALCOHOLIC_CAPACITY;
    }

}
*/
