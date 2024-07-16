package com.example.drinkdeposit.service;


import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.repositories.HistoryDrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class HistoryService {

    private final HistoryDrinkDepositRepository historyRepository;

    public HistoryService(HistoryDrinkDepositRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveHistory(DrinkDeposit drinkDeposit) {
        Stream.of(convertToHistory(drinkDeposit))
                .map(historyRepository::save)
                .findFirst()
                .orElseThrow();
    }

    public List<DrinkHistory> getAll() {
        return Optional.of(historyRepository.findAll()).orElseThrow();
    }


    private DrinkHistory convertToHistory(DrinkDeposit drinkDeposit){
      return new DrinkHistory(drinkDeposit.getData(), drinkDeposit.getResponsible(),
                drinkDeposit.getSection(), drinkDeposit.getMovimentType(), drinkDeposit.getDrink());
    }

}
