package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_DRINK_DEPOSIT")
public class DrinkDeposit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Enumerated
    private DrinkType drinkType;
    private String drinkName;
    private Double volume;
    private LocalDateTime data;

    public DrinkDeposit() {
    }

    public DrinkDeposit(DrinkType drinkType, String drinkName, Double volume, LocalDateTime data) {
        this.drinkType = drinkType;
        this.drinkName = drinkName;
        this.volume = volume;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
