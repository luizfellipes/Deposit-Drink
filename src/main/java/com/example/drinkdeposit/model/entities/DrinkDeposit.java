package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
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
    @Enumerated
    private MovimentType movimentType;
    @Embedded
    private Section section;

    public DrinkDeposit() {
    }

    public DrinkDeposit(LocalDateTime data, MovimentType movimentType, Section section) {
        this.data = data;
        this.movimentType = movimentType;
        this.section = section;
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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

}
