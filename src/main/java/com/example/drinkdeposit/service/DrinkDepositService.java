package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Drink;
import com.example.drinkdeposit.model.enums.DrinkType;
import com.example.drinkdeposit.model.enums.MovimentType;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }


    public DrinkDeposit saveSell(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .filter(drinkDeposit -> drinkDeposit.getMovimentType() == MovimentType.SELL)
                .map(drinkDeposit -> {
                    addDrink(drinkDeposit);
                    return repository.save(drinkDeposit);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a venda !"));
    }

    public DrinkDeposit saveEntry(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .filter(drinkDeposit -> drinkDeposit.getMovimentType() == MovimentType.ENTRY)
                .map(drinkDeposit -> {
                    addDrink(drinkDeposit);
                    return repository.save(drinkDeposit);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a entrada !"));
    }

    public List<String> getVolume() {
        return Optional.of(repository.findTotalVolume()).orElseThrow();
    }

    public List<String> getSectionForSale() {
        return Optional.of(repository.findVolume()).orElseThrow();
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), DTO.responsible(), new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume()));
    }

    public void addDrink(DrinkDeposit drinkDeposit) {
        Stream.of(drinkDeposit)
                .filter(drink -> drinkDeposit.getDrink().getDrinkType().equals(DrinkType.ALCOHOLIC) || drinkDeposit.getDrink().getDrinkType().equals(DrinkType.NONALCOHOLIC))
                .map(drink -> drink.getDrinkSection().add(drink.getDrink().getDrinkType()))
                .findFirst()
                .orElseThrow();
    }

}
