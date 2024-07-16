package com.example.drinkdeposit.repositories;

import com.example.drinkdeposit.model.entities.DrinkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDrinkDepositRepository extends JpaRepository<DrinkHistory, Integer> {
}
