package com.example.drinkdeposit.service;


import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.DrinkDepositHistory;
import com.example.drinkdeposit.repositories.HistoryDrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    private final HistoryDrinkDepositRepository historyRepository;

    public HistoryService(HistoryDrinkDepositRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveHistory(DrinkDeposit drinkDeposit) {
        DrinkDepositHistory history = new DrinkDepositHistory(drinkDeposit.getData(), drinkDeposit.getResponsible(),
                drinkDeposit.getSection(), drinkDeposit.getMovimentType(), drinkDeposit.getDrink());
        historyRepository.save(history);
    }

    public List<DrinkDepositHistory> getAll() {
        return Optional.of(historyRepository.findAll()).orElseThrow();
    }



}
