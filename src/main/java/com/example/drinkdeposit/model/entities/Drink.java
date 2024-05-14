package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Column(insertable=false, updatable=false)
    private int volume;
    private int maximumAlcoholicCapacity;
    private int maximumNonAlcoholicCapacity;
    private int currentAlcoholicVolume;
    private int currentNonAlcoholicVolume;
    private List<DrinkType> drinkHistory;



    public Drink() {
    }

    public Drink(DrinkType drinkType, String drinkName, int volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
        this.maximumAlcoholicCapacity = 500;
        this.maximumNonAlcoholicCapacity = 400;
        currentAlcoholicVolume();
        currentNonAlcoholicVolume();
        this.drinkHistory = new ArrayList<>();
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

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getMaximumAlcoholicCapacity() {
        return maximumAlcoholicCapacity;
    }

    public void setMaximumAlcoholicCapacity(int maximumAlcoholicCapacity) {
        this.maximumAlcoholicCapacity = maximumAlcoholicCapacity;
    }

    public int getMaximumNonAlcoholicCapacity() {
        return maximumNonAlcoholicCapacity;
    }

    public void setMaximumNonAlcoholicCapacity(int maximumNonAlcoholicCapacity) {
        this.maximumNonAlcoholicCapacity = maximumNonAlcoholicCapacity;
    }

    public int getCurrentAlcoholicVolume() {
        return currentAlcoholicVolume;
    }

    public void setCurrentAlcoholicVolume(int currentAlcoholicVolume) {
        this.currentAlcoholicVolume = currentAlcoholicVolume;
    }

    public int getCurrentNonAlcoholicVolume() {
        return currentNonAlcoholicVolume;
    }

    public void setCurrentNonAlcoholicVolume(int currentNonAlcoholicVolume) {
        this.currentNonAlcoholicVolume = currentNonAlcoholicVolume;
    }

    public List<DrinkType> getDrinkHistory() {
        return drinkHistory;
    }

    public void setDrinkHistory(List<DrinkType> drinkHistory) {
        this.drinkHistory = drinkHistory;
    }

    public void currentAlcoholicVolume() {
        if (this.currentAlcoholicVolume <= maximumAlcoholicCapacity) {
            this.currentAlcoholicVolume += currentAlcoholicVolume + volume;
        }
    }

    public void currentNonAlcoholicVolume() {
        if (this.currentNonAlcoholicVolume <= maximumNonAlcoholicCapacity) {
            this.currentNonAlcoholicVolume += currentNonAlcoholicVolume + volume;
        }
    }

}
