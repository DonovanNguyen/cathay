package com.cathay.exchangeflow.controller.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.application.currency.CurrencyListResult;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;

class GetCurrencyListResponseBuilderTest {
    @Test
    void testBuild() {
        // Given:
        Currency usd = new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        Currency eur = new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(2));
        List<Currency> currencyList = List.of(usd, eur);
        CurrencyListResult result = new CurrencyListResult(currencyList);
        GetCurrencyListResponseBuilder builder = new GetCurrencyListResponseBuilder(result);

        // When:
        GetCurrencyListResponse response = builder.build();

        // Then:
        assertNotNull(response);
        assertEquals(2, response.getCurrencies().size());

        GetCurrencyListResponse.Currency usdResp = response.getCurrencies().get(0);
        assertEquals(1L, usdResp.getId());
        assertEquals("USD", usdResp.getCode());
        assertEquals("US Dollar", usdResp.getName());
        assertEquals(1, usdResp.getVersion());

        GetCurrencyListResponse.Currency eurResp = response.getCurrencies().get(1);
        assertEquals(2L, eurResp.getId());
        assertEquals("EUR", eurResp.getCode());
        assertEquals("Euro", eurResp.getName());
        assertEquals(2, eurResp.getVersion());
    }
}
