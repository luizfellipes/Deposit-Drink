package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.DrinkType;

import jakarta.persistence.*;

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
    private Double volume;
    private String drinkName;
    private Double totalVolumeInSection;

    public Drink() {
    }

    public Drink(DrinkType drinkType, String drinkName, Double volume) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        checkNegativeValues(volume);
        this.totalVolumeInSection = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public Double getVolume() {
        return volume;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public Double getTotalVolumeInSection() {
        return totalVolumeInSection;
    }

    private void checkNegativeValues(Double volume) {
        if (volume <= 0) {
            throw new IlegalRequest("Não é permitido volumes a baixo de 1");
        } else {
            this.volume = volume;
        }
    }

    public void totalVolumeInSection(Double volumeInSection) {
        this.totalVolumeInSection = volumeInSection;
    }

}
