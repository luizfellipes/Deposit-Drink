package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.MovimentType;
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
    private LocalDateTime data;
    private String responsible;
    private String section;
    private MovimentType movimentType;
    @OneToOne(cascade = CascadeType.ALL)
    private Drink drink;


    public DrinkDeposit() {
    }

    public DrinkDeposit(Integer id, Drink drink) {
        this.id = id;
        this.drink = drink;
    }

    public DrinkDeposit(LocalDateTime data, String responsible, String section, MovimentType movimentType, Drink drink) {
        this.data = data;
        this.responsible = responsible;
        this.section = section;
        this.movimentType = movimentType;
        this.drink = drink;
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

    public Drink getDrink() {
        return drink;
    }

    public String getSection() {
        return section;
    }
}
