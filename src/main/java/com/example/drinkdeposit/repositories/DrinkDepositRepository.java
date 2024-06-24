package com.example.drinkdeposit.repositories;

import com.example.drinkdeposit.model.entities.DrinkDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface DrinkDepositRepository extends JpaRepository<DrinkDeposit, Integer> {

}
