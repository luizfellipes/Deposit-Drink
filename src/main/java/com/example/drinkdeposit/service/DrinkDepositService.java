package com.example.drinkdeposit.service;

import com.example.drinkdeposit.model.dto.DrinkDepositDTO;
import com.example.drinkdeposit.model.entities.DrinkDeposit;
import com.example.drinkdeposit.model.entities.Section;
import com.example.drinkdeposit.repositories.DrinkDepositRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DrinkDepositService {

    private final DrinkDepositRepository repository;

    public DrinkDepositService(DrinkDepositRepository repository) {
        this.repository = repository;
    }


    public DrinkDeposit save(DrinkDepositDTO drinkDepositDTO) {
        return Stream.of(convertDtoInModel(drinkDepositDTO))
                .map(drinkDeposit -> {
                    drinkDeposit.getSection().addDrink(drinkDeposit.getSection().getDrinkType());
                    return repository.save(convertDtoInModel(drinkDepositDTO));
                })
                .findFirst()
                .orElse(null);
    }

    public List<DrinkDeposit> getAll() {
        return Optional.of(repository.findAll()).orElseThrow();
    }

    public DrinkDeposit convertDtoInModel(DrinkDepositDTO DTO) {
        return new DrinkDeposit(DTO.data(), DTO.movimentType(), new Section(DTO.section().drinkType(), DTO.section().drinkName(), DTO.section().volume()));
    }


}
