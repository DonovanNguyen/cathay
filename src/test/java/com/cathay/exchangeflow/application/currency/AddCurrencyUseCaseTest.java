package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.cathay.exchangeflow.UnitTestSpringBoot;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@UnitTestSpringBoot
class AddCurrencyUseCaseTest {
    @Autowired
    private AddCurrencyUseCase addCurrencyUseCase;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void testExecute_addsCurrencySuccessfully() {
        AddCurrencyCommand command = new AddCurrencyCommand("VND", "Vietnam Dong");
        assertDoesNotThrow(() -> addCurrencyUseCase.execute(command));

        Currency currency = currencyRepository.findById(5L).orElse(null);
        assertNotNull(currency);
        assertEquals("VND", currency.getCode().getValue());
        assertEquals("Vietnam Dong", currency.getName());
    }

    @Test
    void testExecute_duplicateCurrencyThrowsException() {
        // Assume test-data.sql already has USD
        AddCurrencyCommand command = new AddCurrencyCommand("USD", "US Dollar");
        assertThrows(ApplicationException.class, () -> addCurrencyUseCase.execute(command));
    }
}
