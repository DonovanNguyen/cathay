package com.cathay.exchangeflow.controller.currency;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.cathay.exchangeflow.application.currency.AddCurrencyCommand;
import com.cathay.exchangeflow.application.currency.AddCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.CurrencyListResult;
import com.cathay.exchangeflow.application.currency.DeleteCurrencyCommand;
import com.cathay.exchangeflow.application.currency.DeleteCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.EditCurrencyCommand;
import com.cathay.exchangeflow.application.currency.EditCurrencyUseCase;
import com.cathay.exchangeflow.application.currency.GetCurrencyCommand;
import com.cathay.exchangeflow.application.currency.GetCurrencyListUseCase;
import com.cathay.exchangeflow.application.currency.GetCurrencyUseCase;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;

class CurrencyControllerTest {

    private GetCurrencyListUseCase getCurrencyListUseCase;
    private GetCurrencyUseCase getCurrencyUseCase;
    private AddCurrencyUseCase addCurrencyUseCase;
    private EditCurrencyUseCase editCurrencyUseCase;
    private DeleteCurrencyUseCase deleteCurrencyUseCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        getCurrencyListUseCase = mock(GetCurrencyListUseCase.class);
        getCurrencyUseCase = mock(GetCurrencyUseCase.class);
        addCurrencyUseCase = mock(AddCurrencyUseCase.class);
        editCurrencyUseCase = mock(EditCurrencyUseCase.class);
        deleteCurrencyUseCase = mock(DeleteCurrencyUseCase.class);

        CurrencyController controller = new CurrencyController(getCurrencyListUseCase,
                getCurrencyUseCase, addCurrencyUseCase, editCurrencyUseCase, deleteCurrencyUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testList() throws Exception {
        Currency currency = new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        CurrencyListResult result = new CurrencyListResult(List.of(currency));
        when(getCurrencyListUseCase.execute()).thenReturn(result);

        mockMvc.perform(get("/api/currencies")).andExpect(status().isOk())
                .andExpect(jsonPath("$.currencies", hasSize(1)))
                .andExpect(jsonPath("$.currencies[0].id", is(1)))
                .andExpect(jsonPath("$.currencies[0].code", is("USD")))
                .andExpect(jsonPath("$.currencies[0].name", is("US Dollar")))
                .andExpect(jsonPath("$.currencies[0].version", is(1)));
    }

    @Test
    void testGet() throws Exception {
        Currency currency =
                new Currency(1L, com.cathay.exchangeflow.domain.currency.CurrencyCode.of("USD"),
                        "US Dollar", com.cathay.exchangeflow.core.Version.of(1));
        when(getCurrencyUseCase.execute(any(GetCurrencyCommand.class))).thenReturn(currency);

        mockMvc.perform(get("/api/currencies/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.code", is("USD")))
                .andExpect(jsonPath("$.name", is("US Dollar")))
                .andExpect(jsonPath("$.version", is(1)));
    }

    @Test
    void testAdd() throws Exception {
        String json = """
                {
                  "code": "GBP",
                  "name": "British Pound"
                }
                """;
        mockMvc.perform(
                post("/api/currencies").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        ArgumentCaptor<AddCurrencyCommand> captor =
                ArgumentCaptor.forClass(AddCurrencyCommand.class);
        verify(addCurrencyUseCase).execute(captor.capture());
        AddCurrencyCommand cmd = captor.getValue();
        assert cmd.getCurrencyCode().equals("GBP");
        assert cmd.getCurrencyName().equals("British Pound");
    }

    @Test
    void testEdit() throws Exception {
        String json = """
                {
                  "code": "EUR",
                  "name": "Euro",
                  "version": 2
                }
                """;
        mockMvc.perform(
                put("/api/currencies/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        ArgumentCaptor<EditCurrencyCommand> captor =
                ArgumentCaptor.forClass(EditCurrencyCommand.class);
        verify(editCurrencyUseCase).execute(captor.capture());
        EditCurrencyCommand cmd = captor.getValue();
        assert cmd.getId() == 1L;
        assert cmd.getCurrencyCode().equals("EUR");
        assert cmd.getCurrencyName().equals("Euro");
        assert cmd.getVersion() == 2;
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/currencies/1")).andExpect(status().isOk());

        ArgumentCaptor<DeleteCurrencyCommand> captor =
                ArgumentCaptor.forClass(DeleteCurrencyCommand.class);
        verify(deleteCurrencyUseCase).execute(captor.capture());
        assert captor.getValue().getId() == 1L;
    }
}
