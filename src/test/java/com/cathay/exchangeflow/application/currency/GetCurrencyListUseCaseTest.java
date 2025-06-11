package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.cathay.exchangeflow.UnitTestSpringBoot;
import com.cathay.exchangeflow.domain.currency.Currency;

@UnitTestSpringBoot
class GetCurrencyListUseCaseTest {
    @Autowired
    private GetCurrencyListUseCase getCurrencyListUseCase;

    @Test
    void testExecute_returnsAllCurrencies_orderedByCode() {
        CurrencyListResult result = getCurrencyListUseCase.execute();
        List<Currency> currencies = result.getCurrencies();

        assertNotNull(currencies);
        assertFalse(currencies.isEmpty());
        assertEquals(4, currencies.size());
        assertEquals("EUR", currencies.get(0).getCode().getValue());
        assertEquals("GBP", currencies.get(1).getCode().getValue());
        assertEquals("JPY", currencies.get(2).getCode().getValue());
        assertEquals("USD", currencies.get(3).getCode().getValue());
    }
}
