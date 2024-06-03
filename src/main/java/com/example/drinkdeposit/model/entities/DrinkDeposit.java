package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private MovimentType movimentType;
    private String responsible;
    @Value("${drink.section}")
    private String[] section;
    @OneToOne(cascade = CascadeType.ALL)
    private Drink drink;
    @Column(insertable = false, updatable = false)
    private DrinkHistory drinkHistory;

    public DrinkDeposit() {
    }

    public DrinkDeposit(Integer id, String[] section) {
        this.id = id;
        this.section = section;
    }

    public DrinkDeposit(Integer id, Drink drink) {
        this.id = id;
        this.drink = drink;
    }

    public DrinkDeposit(LocalDateTime data, MovimentType movimentType, String responsible, String[] section, Drink drink) {
        this.data = data;
        this.movimentType = movimentType;
        this.responsible = responsible;
        this.drink = drink;
        this.section = section;
    }

    public DrinkDeposit(Integer id, LocalDateTime data, Drink drink, DrinkHistory drinkHistory) {
        this.id = id;
        this.data = data;
        this.drink = drink;
        this.drinkHistory = drinkHistory;

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

    public String[] getSection() {
        return section;
    }

    public void setSection(String[] section) {
        this.section = section;
    }

    public void setDrinkHistory(DrinkHistory drinkHistory) {
        this.drinkHistory = drinkHistory;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }


}
