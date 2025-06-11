package com.cathay.exchangeflow.controller.exchangerate;

import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateCommand;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateResult;
import com.cathay.exchangeflow.application.exchangerate.RetrieveExchangeRateUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ExchangeRateControllerTest {

    private RetrieveExchangeRateUseCase retrieveExchangeRateUseCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        retrieveExchangeRateUseCase = mock(RetrieveExchangeRateUseCase.class);
        ExchangeRateController controller = new ExchangeRateController(retrieveExchangeRateUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetExchangeRate_success() throws Exception {
        RetrieveExchangeRateResult mockResult = mock(RetrieveExchangeRateResult.class);
        when(retrieveExchangeRateUseCase.execute(any(RetrieveExchangeRateCommand.class)))
                .thenReturn(mockResult);

        mockMvc.perform(get("/api/exchange-rates").param("base", "USD").param("quote", "EUR")
                .param("startDate", "2025-06-10").param("endDate", "2025-06-11")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        ArgumentCaptor<RetrieveExchangeRateCommand> captor =
                ArgumentCaptor.forClass(RetrieveExchangeRateCommand.class);
        verify(retrieveExchangeRateUseCase).execute(captor.capture());
        RetrieveExchangeRateCommand cmd = captor.getValue();
        assert cmd.getBaseCurrency().equals("USD");
        assert cmd.getQuoteCurrency().equals("EUR");
        assert cmd.getStartDate().equals(LocalDate.of(2025, 6, 10));
        assert cmd.getEndDate().equals(LocalDate.of(2025, 6, 11));
    }

    @Test
    void testGetExchangeRate_validationError() throws Exception {
        // Missing required param 'base'
        mockMvc.perform(
                get("/api/exchange-rates").param("quote", "EUR").param("startDate", "2025-06-10")
                        .param("endDate", "2025-06-11").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
