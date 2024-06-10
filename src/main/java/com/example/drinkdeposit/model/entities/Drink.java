package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Stream;

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
    private int MAX_ALCOHOLIC_CAPACITY = 500;
    private int MAX_NONALCOHOLIC_CAPACITY = 400;
    private DrinkDeposit drinkDeposit;

    public Drink() {
    }

    public Drink(Integer id, int MAX_ALCOHOLIC_CAPACITY, int MAX_NONALCOHOLIC_CAPACITY) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
    }

    public Drink(DrinkType drinkType, String drinkName, int volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
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


    public void updateCurrentVolumes(MovimentType movimentType) {
        Stream.of(DrinkType.ALCOHOLIC, DrinkType.NONALCOHOLIC)
                .filter(drinkType1 -> this.currentAlcoholicVolume + this.volume <= this.MAX_ALCOHOLIC_CAPACITY || this.currentNonAlcoholicVolume + this.volume <= this.MAX_NONALCOHOLIC_CAPACITY)
                .map(drinkType1 -> {
                    if (movimentType.equals(MovimentType.SELL)) {
                        sellDrink();
                    } else {
                        entryDrink();
                    }
                    return 0;
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não é possivel adicionar o volume, pois excedeu a capacidade de bebidas !"));
    }

    public boolean isSectionFull() {
        if (drinkType == DrinkType.ALCOHOLIC) {
            return this.currentAlcoholicVolume <= this.MAX_ALCOHOLIC_CAPACITY;
        } else {
            return this.currentNonAlcoholicVolume <= this.MAX_NONALCOHOLIC_CAPACITY;
        }
    }


    public void entryDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume > 0) {
            this.currentAlcoholicVolume += this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume > 0) {
            this.currentNonAlcoholicVolume += this.volume;
        }else {
            throw new RuntimeException("não é possivel vender com volume receber bebida com volume menor que 1");
        }
    }

    public void sellDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume > 0) {
            this.currentAlcoholicVolume -= this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume > 0) {
            this.currentNonAlcoholicVolume -= this.volume;
        } else {
            throw new RuntimeException("não é possivel vender com volume abaixo de 1");
        }
    }

}
