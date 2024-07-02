package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.exceptions.IlegalRequest;
import com.example.drinkdeposit.exceptions.RequestsValidation;
import com.example.drinkdeposit.service.DrinkDepositService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.drinkdeposit.MocksDTO.DrinkDepositDTOMock.drinkDepositDtoMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class DrinkDepositControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private DrinkDepositService drinkDepositService;

    @BeforeEach
    void setUp() {
        this.drinkDepositService = mock(DrinkDepositService.class);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new DrinkDepositController(drinkDepositService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new RequestsValidation()).build();
        this.objectMapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
    }

    @Test
    void makeTestOnNewCreate() throws Exception {
        when(drinkDepositService.save(drinkDepositDtoMock())).thenReturn(any());

        mockMvc.perform(post("/drinkdeposit/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(drinkDepositDtoMock())))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void doTestErrorOnNewCreate() throws Exception {
        when(drinkDepositService.save(any())).thenReturn(any());

        mockMvc.perform(post("/drinkdeposit/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(any())))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    void makeTestOnGetAll() throws Exception {
        when(drinkDepositService.getAll()).thenReturn(List.of());

        mockMvc.perform(get("/drinkdeposit"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void doTestErrorOnGetAll() throws Exception {
        doThrow(IlegalRequest.class).when(drinkDepositService).getAll();

        mockMvc.perform(get("/drinkdeposit"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    void makeTestOnGetAllVolumes() throws Exception {
        when(drinkDepositService.getAllVolumesPerDrink()).thenReturn(List.of());

        mockMvc.perform(get("/drinkdeposit/volume"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void doTestErrorOnGetAllVolumes() throws Exception {
        doThrow(IlegalRequest.class).when(drinkDepositService).getAllVolumesPerDrink();

        mockMvc.perform(get("/drinkdeposit/volume"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
