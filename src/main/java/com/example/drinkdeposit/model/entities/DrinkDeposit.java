package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

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
    @Value("${drink.section}")
    private List<String> section;
    @OneToOne(cascade = CascadeType.ALL)
    private Drink drink;
    private DrinkHistory drinkHistory;


    public DrinkDeposit() {
    }

    public DrinkDeposit(Integer id, List<String> section) {
        this.id = id;
        this.section = section;
    }

    public DrinkDeposit(Integer id, Drink drink) {
        this.id = id;
        this.drink = drink;
    }

    public DrinkDeposit(LocalDateTime data, MovimentType movimentType, String responsible, List<String> section, Drink drink) {
        this.data = data;
        this.movimentType = movimentType;
        this.responsible = responsible;
        this.section = section;
        this.drink = drink;
        drink.updateCurrentVolumes(movimentType);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public MovimentType getMovimentType() {
        return movimentType;
    }

    public String getResponsible() {
        return responsible;
    }

    public List<String> getSection() {
        return section;
    }

    public Drink getDrink() {
        return drink;
    }

    public DrinkHistory getDrinkHistory() {
        return drinkHistory;
    }

   /*public void addSection(){
    section.add(drink.getVolume(), drink.getCurrentAlcoholicVolume(), drink.getCurrentNonAlcoholicVolume());
    }*/


}
