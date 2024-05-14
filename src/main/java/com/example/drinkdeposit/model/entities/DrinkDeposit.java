package com.example.drinkdeposit.model.entities;


import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_DRINK_DEPOSIT")
public class DrinkDeposit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private MovimentType movimentType;
    private String responsible;
    @OneToOne(cascade = CascadeType.ALL)
    private Drink drink;
    private DrinkHistory drinkHistory;
    private List<DrinkType> drinkSection;

    public DrinkDeposit() {
    }


    public DrinkDeposit(Integer id, Drink drink) {
        this.id = id;
        this.drink = drink;
    }

    public DrinkDeposit(LocalDateTime data, MovimentType movimentType, String responsible, Drink drink) {
        this.data = data;
        this.movimentType = movimentType;
        this.responsible = responsible;
        this.drink = drink;
        this.drinkSection = new ArrayList<>();
    }

    public DrinkDeposit(Integer id, LocalDateTime data, Drink drink, DrinkHistory drinkHistory, List<DrinkType> drinkSection) {
        this.id = id;
        this.data = data;
        this.drink = drink;
        this.drinkHistory = drinkHistory;
        this.drinkSection = drinkSection;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public MovimentType getMovimentType() {
        return movimentType;
    }

    public void setMovimentType(MovimentType movimentType) {
        this.movimentType = movimentType;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public DrinkHistory getDrinkHistory() {
        return drinkHistory;
    }

    public void setDrinkHistory(DrinkHistory drinkHistory) {
        this.drinkHistory = drinkHistory;
    }

    public List<DrinkType> getDrinkSection() {
        return drinkSection;
    }

    public void setDrinkSection(List<DrinkType> drinkSection) {
        this.drinkSection = drinkSection;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
