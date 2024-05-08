package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Section;
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
                    drinkDeposit.getSection().addDrink(drinkDeposit.getSection().getDrinkType());
                    return repository.save(drinkDeposit);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a venda !"));
    }

    public DrinkDeposit saveEntry(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .filter(drinkDeposit -> drinkDeposit.getMovimentType() == MovimentType.ENTRY)
                .map(drinkDeposit -> {
                    drinkDeposit.getSection().addDrink(drinkDeposit.getSection().getDrinkType());
                    return repository.save(drinkDeposit);
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("não foi possivel realizar a entrada !"));
    }

    public List<String> getVolume() {
        return Optional.of(repository.findTotalVolume()).orElseThrow();
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), new Section(DTO.section().drinkType(), DTO.section().drinkName(), DTO.section().volume()));
    }


}
