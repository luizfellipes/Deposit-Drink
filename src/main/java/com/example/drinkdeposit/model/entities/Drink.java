package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;


import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_DRINK")
public class Drink implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;
    private String drinkName;
    @Column(insertable = false, updatable = false)
    private int volume;
    private int currentAlcoholicVolume;
    private int currentNonAlcoholicVolume;
    @Value("${alcoholic.capacity:500}")
    private int MAX_ALCOHOLIC_CAPACITY;
    @Value("${nonAlcoholic.capacity:400}")
    private int MAX_NONALCOHOLIC_CAPACITY;

    public Drink() {
    }

    public Drink(DrinkType drinkType, String drinkName, int volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
        currentAlcoholicVolume();
        currentNonAlcoholicVolume();
    }

    public Integer getId() {
        return id;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getVolume() {
        return volume;
    }

    public int getCurrentAlcoholicVolume() {
        return currentAlcoholicVolume;
    }

    public int getCurrentNonAlcoholicVolume() {
        return currentNonAlcoholicVolume;
    }


    private void currentAlcoholicVolume() {
        if (this.currentAlcoholicVolume <= this.MAX_ALCOHOLIC_CAPACITY) {
            this.currentAlcoholicVolume += this.currentAlcoholicVolume + this.volume;
        }
    }

    private void currentNonAlcoholicVolume() {
        if (this.currentNonAlcoholicVolume <= this.MAX_NONALCOHOLIC_CAPACITY) {
            this.currentNonAlcoholicVolume += this.currentNonAlcoholicVolume + this.volume;
        }
    }

}
