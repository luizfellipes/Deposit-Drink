package com.example.drinkdeposit.controller;

import com.example.drinkdeposit.exceptions.EntryError;
import com.example.drinkdeposit.exceptions.RequestsValidation;
import com.example.drinkdeposit.service.DrinkConfigService;
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

import static com.example.drinkdeposit.MocksDTO.DrinkConfigDTOMock.drinkConfigDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class DrinkConfigControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private DrinkConfigService drinkConfigService;

    @BeforeEach
    void setUp() {
        this.drinkConfigService = mock(DrinkConfigService.class);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new DrinkConfigController(drinkConfigService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new RequestsValidation()).build();
        this.objectMapper = new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .registerModule(new JavaTimeModule());
    }

    @Test
    void makePatchingSections() throws Exception {
        when(drinkConfigService.patchingDrinkConfig(any())).thenReturn(any());

        mockMvc.perform(patch("/drinkdeposit/sections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(drinkConfigDTO())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void doErrorOnMakePatchingSections() throws Exception {
       doThrow(EntryError.class).when(drinkConfigService).patchingDrinkConfig(drinkConfigDTO());

        mockMvc.perform(patch("/drinkdeposit/sections")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(drinkConfigDTO())))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn();
    }


}
