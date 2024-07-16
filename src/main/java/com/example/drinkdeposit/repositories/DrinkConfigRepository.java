package com.example.drinkdeposit.repositories;

import com.example.drinkdeposit.model.entities.DrinkConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkConfigRepository extends JpaRepository<DrinkConfig, Integer> {

}
