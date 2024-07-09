package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.DrinkType;

import com.example.drinkdeposit.model.enums.MovimentType;
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
    @Lob
    @Column(name = "drink_config", columnDefinition = "BLOB")
    private DrinkConfig drinkConfig;


    public Drink() {
    }

    public Drink(DrinkType drinkType, String drinkName, Double volume) {
        iniciateConfig();
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        checkNegativeValues(volume);
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

    public Double getTotalVolumeInSection() {
        return totalVolumeInSection;
    }

    public DrinkConfig getDrinkConfig() {
        return drinkConfig;
    }

    private void checkNegativeValues(Double volume) {
        if (volume <= 0) {
            throw new IlegalRequest("Não é permitido volumes a baixo de 0");
        } else {
            this.volume = volume;
        }
    }

    public Double maxCapacity() {
        if (drinkType.equals(DrinkType.ALCOHOLIC)) {
            return this.getDrinkConfig().getMAX_ALCOHOLIC_CAPACITY();
        } else {
            return this.getDrinkConfig().getMAX_NONALCOHOLIC_CAPACITY();
        }
    }

    public void totalVolumeInSection(Double volumeInSection) {
        this.totalVolumeInSection = volumeInSection;
    }

    private void iniciateConfig() {
        this.drinkConfig = new DrinkConfig();
    }

}
