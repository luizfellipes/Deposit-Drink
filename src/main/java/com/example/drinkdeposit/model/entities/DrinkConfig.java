package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_DRINK_CONFIG")
@Configuration
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Double MAX_ALCOHOLIC_CAPACITY;
    private Double MAX_NONALCOHOLIC_CAPACITY;
    private boolean DRINK_CAN_BE_TOGETHER;
    private List<String> PERMIT_SECTION;

    public DrinkConfig() {
        this(1, 500.0, 400.0, List.of("A", "B", "C", "D", "E"), false);
    }

    public DrinkConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, List<String> permitSection, boolean drinksCanBeTogether) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = maxAlcoholicCapacity;
        this.MAX_NONALCOHOLIC_CAPACITY = maxNonAlcoholicCapacity;
        this.PERMIT_SECTION = permitSection;
        this.DRINK_CAN_BE_TOGETHER = drinksCanBeTogether;
    }

    public Integer getId() {
        return id;
    }

    public Double getMAX_ALCOHOLIC_CAPACITY() {
        return MAX_ALCOHOLIC_CAPACITY;
    }

    public Double getMAX_NONALCOHOLIC_CAPACITY() {
        return MAX_NONALCOHOLIC_CAPACITY;
    }

    public List<String> getPERMIT_SECTION() {
        return PERMIT_SECTION;
    }

    public boolean isDRINK_CAN_BE_TOGETHER() {
        return DRINK_CAN_BE_TOGETHER;
    }

    public void setMAX_ALCOHOLIC_CAPACITY(Double MAX_ALCOHOLIC_CAPACITY) {
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
    }

    public void setMAX_NONALCOHOLIC_CAPACITY(Double MAX_NONALCOHOLIC_CAPACITY) {
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
    }

    public void setDRINK_CAN_BE_TOGETHER(boolean DRINK_CAN_BE_TOGETHER) {
        this.DRINK_CAN_BE_TOGETHER = DRINK_CAN_BE_TOGETHER;
    }

    public void setPERMIT_SECTION(List<String> PERMIT_SECTION) {
        this.PERMIT_SECTION = PERMIT_SECTION;
    }

    public Double maxCapacity(DrinkType drinkType) {
        return DrinkType.ALCOHOLIC.equals(drinkType) ? this.MAX_ALCOHOLIC_CAPACITY : this.MAX_NONALCOHOLIC_CAPACITY;
    }
}
