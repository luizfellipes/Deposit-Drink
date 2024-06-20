package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
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
    @Column(insertable = true, updatable = true)
    private int volume;
    private int currentAlcoholicVolume;
    private int currentNonAlcoholicVolume;
    @OneToOne(cascade = CascadeType.ALL)
    private DrinkConfig drinkConfig;


    public Drink() {
    }

    public Drink(DrinkConfig drinkConfig) {
        this.drinkConfig = drinkConfig;
    }

    public Drink(DrinkType drinkType, String drinkName, int volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
        this.drinkConfig = new DrinkConfig();
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
                .filter(drinkType1 -> this.currentAlcoholicVolume + this.volume <= drinkConfig.getMAX_ALCOHOLIC_CAPACITY() || this.currentNonAlcoholicVolume + this.volume <= drinkConfig.getMAX_NONALCOHOLIC_CAPACITY())
                .map(drinkType1 -> {
                    if (movimentType.equals(MovimentType.SELL)) {
                        sellDrink();
                    } else {
                        entryDrink();
                    }
                    return 0;
                })
                .findFirst()
                .orElseThrow(() -> new IlegalRequest("não é possivel adicionar o volume, pois excedeu a capacidade de bebidas !"));
    }

    private boolean isSectionFull() {
        if (drinkType == DrinkType.ALCOHOLIC) {
            return this.currentAlcoholicVolume <= drinkConfig.getMAX_ALCOHOLIC_CAPACITY();
        } else {
            return this.currentNonAlcoholicVolume <= drinkConfig.getMAX_NONALCOHOLIC_CAPACITY();
        }
    }

    private void entryDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume >= 0) {
            this.currentAlcoholicVolume += this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume >= 0) {
            this.currentNonAlcoholicVolume += this.volume;
        } else {
            throw new IlegalRequest("não é possivel vender com volume receber bebida com volume menor que 1");
        }
    }

    private void sellDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume > 0) {
            this.currentAlcoholicVolume -= this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume > 0) {
            this.currentNonAlcoholicVolume -= this.volume;
        } else {
            throw new IlegalRequest("não é possivel vender com volume abaixo de 1");
        }
    }

}
