package com.example.drinkdeposit.service;


import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.repositories.DrinkDepositHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkHistoryService {

    private final DrinkDepositHistoryRepository historyRepository;

    public DrinkHistoryService(DrinkDepositHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public DrinkHistory saveHistory(DrinkDeposit drinkDeposit) {
        return Stream.of(convertToHistory(drinkDeposit))
                .map(historyRepository::save)
                .findFirst()
                .orElseThrow();
    }

    public List<DrinkHistory> getAll() {
        return Optional.of(historyRepository.findAll()).orElseThrow();
    }


    private DrinkHistory convertToHistory(DrinkDeposit drinkDeposit) {
        return new DrinkHistory(drinkDeposit.getData(), drinkDeposit.getResponsible(),
                drinkDeposit.getSection(), drinkDeposit.getMovimentType(), drinkDeposit.getDrink());
    }

}
