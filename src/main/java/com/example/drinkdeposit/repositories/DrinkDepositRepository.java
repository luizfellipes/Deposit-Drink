package com.example.drinkdeposit.repositories;

import com.example.drinkdeposit.model.entities.DrinkDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DrinkDepositRepository extends JpaRepository<DrinkDeposit, Integer> {

    @Query(value = "SELECT DRINK_TYPE, CAST(SUM(VOLUME) AS DECIMAL(20, 2))\n" +
            "FROM TB_DRINK_DEPOSIT\n" +
            "GROUP BY DRINK_TYPE;", nativeQuery = true)
    List<String> findTotalVolume();

    @Query(value = "SELECT VOLUME FROM TB_DRINK_DEPOSIT GROUP BY DRINK_TYPE", nativeQuery = true)
    List<String> findVolume();
}
