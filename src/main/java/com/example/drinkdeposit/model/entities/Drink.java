package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
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
    @Column(insertable = true, updatable = true)
    private int volume;
    private int currentAlcoholicVolume;
    private int currentNonAlcoholicVolume;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public DrinkConfig getDrinkConfig() {
        return drinkConfig;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void updateCurrentVolumes(MovimentType movimentType) {
        Stream.of(movimentType)
                .filter(drinkType1 -> isSectionFull())
                .map(drinkType1 -> drinkType1.equals(MovimentType.SELL) ? sellDrink() : entryDrink())
                .findFirst()
                .orElseThrow(() -> new IlegalRequest("não é possivel adicionar o volume, pois excedeu a capacidade de bebidas !"));
    }

    public boolean isSectionFull() {
        return getCurrentVolume() + volume <= maxCapacity() && volume >= getCurrentVolume();
    }

    private int entryDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume >= 0) {
            return this.currentAlcoholicVolume += this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume >= 0) {
            return this.currentNonAlcoholicVolume += this.volume;
        } else {
            throw new IlegalRequest("não é possivel receber bebida com volume menor que 1");
        }
    }

    private int sellDrink() {
        if (DrinkType.ALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentAlcoholicVolume > 0) {
            return this.currentAlcoholicVolume -= this.volume;
        } else if (DrinkType.NONALCOHOLIC.equals(drinkType) && isSectionFull() && this.currentNonAlcoholicVolume > 0) {
            return this.currentNonAlcoholicVolume -= this.volume;
        } else {
            throw new IlegalRequest("não é possivel vender com volume abaixo de 1");
        }
    }

    public int maxCapacity() {
        return drinkType.equals(DrinkType.ALCOHOLIC) ? drinkConfig.getMAX_ALCOHOLIC_CAPACITY() : drinkConfig.getMAX_NONALCOHOLIC_CAPACITY();
    }

    private int getCurrentVolume() {
        return DrinkType.ALCOHOLIC.equals(drinkType) ? currentAlcoholicVolume : currentNonAlcoholicVolume;
    }


}
