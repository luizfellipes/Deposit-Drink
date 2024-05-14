package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;


public class DrinkHistory implements Serializable {


    @Enumerated(EnumType.STRING)
    private MovimentType movimentType;
    private LocalDateTime data;
    private Integer drinkId;
    @Column(insertable=false, updatable=false)
    private Double volume;
    private Drink drink;
    private String response;

    public DrinkHistory() {
    }

    public DrinkHistory(MovimentType movimentType, LocalDateTime data, Integer drinkId, Double volume, Drink drink, String response) {
        this.movimentType = movimentType;
        this.data = data;
        this.drinkId = drinkId;
        this.volume = volume;
        this.drink = drink;
        this.response = response;
    }

    public MovimentType getMovimentType() {
        return movimentType;
    }

    public void setMovimentType(MovimentType movimentType) {
        this.movimentType = movimentType;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
