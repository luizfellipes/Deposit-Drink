package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Section implements Serializable {


    @Enumerated(EnumType.STRING)
    private DrinkType drinkType;
    private String drinkName;
    private Double volume;
    private List<DrinkType> drink;
    private int maximumAlcoholicCapacity;
    private int maximumNonAlcoholicCapacity;
    private int currentAlcoholicVolume;
    private int currentNonAlcoholicVolume;
    private DrinkDeposit drinkDeposit;

    public Section() {
    }

    public Section(DrinkType drinkType, String drinkName, Double volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
        this.drink = new ArrayList<>();
        this.maximumAlcoholicCapacity = 500;
        this.maximumNonAlcoholicCapacity = 400;
        this.currentAlcoholicVolume = 0;
        this.currentNonAlcoholicVolume = 0;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }


    public void addDrink(DrinkType drink) {
        if (drink.equals(DrinkType.ALCOHOLIC)) {
            if (currentAlcoholicVolume + volume <= maximumAlcoholicCapacity) {
                this.drink.add(drink);
                currentAlcoholicVolume += volume;

            }
        } else {
            if (currentNonAlcoholicVolume + volume <= maximumNonAlcoholicCapacity) {
                this.drink.add(drink);
                currentNonAlcoholicVolume += volume;
            }
        }
    }

}
