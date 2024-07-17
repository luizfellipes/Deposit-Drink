package com.example.drinkdeposit.model.entities;

import com.example.drinkdeposit.model.enums.DrinkType;
import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TB_DRINK_CONFIG")
@Configuration
public class DrinkConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double MAX_ALCOHOLIC_CAPACITY;
    private Double MAX_NONALCOHOLIC_CAPACITY;
    private Set<String> PERMIT_SECTION;

    public DrinkConfig() {
        this.id = 1;
        this.MAX_ALCOHOLIC_CAPACITY = 500.0;
        this.MAX_NONALCOHOLIC_CAPACITY = 400.0;
        this.PERMIT_SECTION = Set.of("A", "B", "C", "D", "E");
    }

    public DrinkConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, Set<String> permitSection) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = maxAlcoholicCapacity;
        this.MAX_NONALCOHOLIC_CAPACITY = maxNonAlcoholicCapacity;
        this.PERMIT_SECTION = permitSection;
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

    public Set<String> getPERMIT_SECTION() {
        return PERMIT_SECTION;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMAX_ALCOHOLIC_CAPACITY(Double MAX_ALCOHOLIC_CAPACITY) {
        this.MAX_ALCOHOLIC_CAPACITY = MAX_ALCOHOLIC_CAPACITY;
    }

    public void setMAX_NONALCOHOLIC_CAPACITY(Double MAX_NONALCOHOLIC_CAPACITY) {
        this.MAX_NONALCOHOLIC_CAPACITY = MAX_NONALCOHOLIC_CAPACITY;
    }

    public void setPERMIT_SECTION(Set<String> PERMIT_SECTION) {
        this.PERMIT_SECTION = PERMIT_SECTION;
    }

    public Double maxCapacity(DrinkType drinkType) {
        if (DrinkType.ALCOHOLIC.equals(drinkType)) {
            return this.MAX_ALCOHOLIC_CAPACITY;
        } else {
            return this.MAX_NONALCOHOLIC_CAPACITY;
        }
    }

}
