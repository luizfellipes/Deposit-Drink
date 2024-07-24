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
    private boolean DRINK_CAN_BE_TOGETHER;
    private Set<String> PERMIT_SECTION;

    public DrinkConfig() {
        initializeConfig(1, 500.0, 400.0, Set.of("A", "B", "C", "D", "E"), false);
    }

    public DrinkConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, Set<String> permitSection, boolean drinksCanBeTogether) {
        initializeConfig(id, maxAlcoholicCapacity, maxNonAlcoholicCapacity, permitSection, drinksCanBeTogether);
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

    public boolean isDRINK_CAN_BE_TOGETHER() {
        return DRINK_CAN_BE_TOGETHER;
    }

    private void initializeConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, Set<String> permitSection, boolean drinksCanBeTogether) {
        this.id = id;
        this.MAX_ALCOHOLIC_CAPACITY = maxAlcoholicCapacity;
        this.MAX_NONALCOHOLIC_CAPACITY = maxNonAlcoholicCapacity;
        this.PERMIT_SECTION = permitSection;
        this.DRINK_CAN_BE_TOGETHER = drinksCanBeTogether;
    }

    public void updateConfig(Integer id, Double maxAlcoholicCapacity, Double maxNonAlcoholicCapacity, Set<String> permitSection, boolean drinksCanBeTogether) {
        initializeConfig(id, maxAlcoholicCapacity, maxNonAlcoholicCapacity, permitSection, drinksCanBeTogether);
    }

    public Double maxCapacity(DrinkType drinkType) {
        return DrinkType.ALCOHOLIC.equals(drinkType) ? this.MAX_ALCOHOLIC_CAPACITY : this.MAX_NONALCOHOLIC_CAPACITY;
    }
}
