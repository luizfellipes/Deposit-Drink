package com.example.drinkdeposit.model.entities;


import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "TB_DRINK_HISTORY")
public class DrinkHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDateTime data;
    private String responsible;
    private String section;
    private MovimentType movimentType;
    private DrinkType drinkType;
    private Double volume;
    private String drinkName;
    private Double totalVolumeInSection;

    public DrinkHistory() {
    }


    public DrinkHistory(LocalDateTime data, String responsible, String section, MovimentType movimentType, Drink drink) {
        this.data = data;
        this.responsible = responsible;
        this.section = section;
        this.movimentType = movimentType;
        this.drinkType = drink.getDrinkType();
        this.volume = drink.getVolume();
        this.drinkName = drink.getDrinkName();
        this.totalVolumeInSection = drink.getTotalVolumeInSection();
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getSection() {
        return section;
    }

    public MovimentType getMovimentType() {
        return movimentType;
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
}


