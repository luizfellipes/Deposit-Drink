package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.model.enums.MovimentType;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
    @Value("${drink.section}")
    private List<String> section;
    @OneToOne(cascade = CascadeType.ALL)
    private Drink drink;
    private DrinkHistory drinkHistory;
    @ElementCollection
    private Map<String, Double> sectionVolumes = new HashMap<>();


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

    public Map<String, Double> getSectionVolumes() {
        return sectionVolumes;
    }

    public void updateSectionVolume() {
        if (drink.isSectionFull()) {
            throw new IlegalRequest("Seção cheia, não foi possivel adicionar mais bebidas.");
        } else {
            sectionVolumes.put(String.valueOf(section), (double) drink.getVolume());
        }
    }

}
