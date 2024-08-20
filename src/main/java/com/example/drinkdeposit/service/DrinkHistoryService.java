package com.example.drinkdeposit.service;


import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.DrinkHistory;
import com.example.drinkdeposit.repositories.DrinkDepositHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkHistoryService {

    private final DrinkDepositHistoryRepository historyRepository;
    private static final Logger log = LoggerFactory.getLogger(DrinkHistoryService.class);

    public DrinkHistoryService(DrinkDepositHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public DrinkHistory saveHistory(DrinkDeposit drinkDeposit) {
        log.info("saved history of deposit");
        return Stream.of(convertToHistory(drinkDeposit))
                .map(historyRepository::save)
                .findFirst()
                .orElseThrow();
    }

    public List<DrinkHistory> getAll() {
        log.info("getting history of deposit");
        return Optional.of(historyRepository.findAll()).orElseThrow();
    }

    private DrinkHistory convertToHistory(DrinkDeposit drinkDeposit) {
        return new DrinkHistory(drinkDeposit.getData(), drinkDeposit.getResponsible(),
                drinkDeposit.getSection(), drinkDeposit.getMovimentType(), drinkDeposit.getDrink());
    }

}
