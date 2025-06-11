package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.cathay.exchangeflow.UnitTestSpringBoot;
import com.cathay.exchangeflow.application.exception.DataNotFoundException;
import com.cathay.exchangeflow.domain.currency.Currency;

@UnitTestSpringBoot
class GetCurrencyUseCaseTest {

    @Autowired
    private GetCurrencyUseCase getCurrencyUseCase;

    @Test
    void testExecute_returnsCurrency_whenFound() {
        GetCurrencyCommand command = new GetCurrencyCommand(1L);
        Currency currency = getCurrencyUseCase.execute(command);

        assertNotNull(currency);
        assertEquals(1L, currency.getId());
        assertEquals("EUR", currency.getCode().getValue());
        assertEquals("Euro", currency.getName());
        assertEquals(2, currency.getVersion().getValue());
    }

    @Test
    void testExecute_throwsException_whenNotFound() {
        GetCurrencyCommand command = new GetCurrencyCommand(999L);
        assertThrows(DataNotFoundException.class, () -> getCurrencyUseCase.execute(command));
    }
}
