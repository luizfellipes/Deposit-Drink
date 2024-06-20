package com.example.drinkdeposit.repositories;

import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.enums.DrinkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DrinkDepositRepository extends JpaRepository<DrinkDeposit, Integer> {
/*
    @Query("SELECT SUM(d.volume) FROM Drink d WHERE d.drinkType = :drinkType")
    int findTotalVolumeByDrinkType(DrinkType drinkType);

    @Query("SELECT s FROM Section s WHERE s.capacity - s.currentVolume >= :volume")
    List<DrinkDeposit> findAvailableSections(int volume);*/
}
