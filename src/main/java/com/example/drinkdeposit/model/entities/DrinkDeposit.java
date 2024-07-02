package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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
    @ElementCollection
    private final Map<String, Integer> sectionVolumes = new HashMap<>();


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

    public Drink getDrink() {
        return drink;
    }

    public Map<String, Integer> getSectionVolumes() {
        return sectionVolumes;
    }

    public Map<String, Integer> updateSectionVolume() {
        if (drink.isSectionFull()) {
            throw new IlegalRequest("Seção cheia, não foi possivel adicionar mais bebidas.");
        } else {
            sectionVolumes.put("Section: " + drink.getSection(), drink.getVolume());
            return sectionVolumes;
        }
    }

}
