package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Drink;
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
                .map(repository::save)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a entrada !"));
    }

    public DrinkDeposit saveEntry(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .filter(drinkDeposit -> drinkDeposit.getMovimentType() == MovimentType.ENTRY)
                .map(repository::save)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a entrada !"));
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), DTO.responsible(), DTO.section(), new Drink(DTO.drink().drinkType(), DTO.drink().drinkName(), DTO.drink().volume()));
    }


}

/*public void addDrink(DrinkDeposit drinkDeposit) {
    Stream.of(drinkDeposit)
            .filter(drink -> drinkDeposit.getDrink().getDrinkType().equals(DrinkType.ALCOHOLIC) || drinkDeposit.getDrink().getDrinkType().equals(DrinkType.NONALCOHOLIC))
            .map(drink -> drink.
            )
            .findFirst()
}

        }
            .orElseThrow();*/
