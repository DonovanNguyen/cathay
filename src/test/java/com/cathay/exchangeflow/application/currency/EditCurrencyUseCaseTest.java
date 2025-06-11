package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.cathay.exchangeflow.UnitTestSpringBoot;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.application.exception.DataNotFoundException;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@UnitTestSpringBoot
class EditCurrencyUseCaseTest {

    @Autowired
    private EditCurrencyUseCase editCurrencyUseCase;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void testExecute_success() {
        EditCurrencyCommand command = new EditCurrencyCommand(1L, "AUD", "Australian Dollar", 2);

        assertDoesNotThrow(() -> editCurrencyUseCase.execute(command));

        Currency updated = currencyRepository.findById(1L).orElseThrow();
        assertEquals(1L, updated.getId());
        assertEquals("AUD", updated.getCode().getValue());
        assertEquals("Australian Dollar", updated.getName());
    }

    @Test
    void testExecute_whenDataNotFound_throwsDataNotFoundException() {
        EditCurrencyCommand command = new EditCurrencyCommand(999L, "EUR", "Euro", 1);

        assertThrows(DataNotFoundException.class, () -> editCurrencyUseCase.execute(command));
    }

    @Test
    void testExecute_whenDuplicatedCode_throwsApplicationException() {
        EditCurrencyCommand command = new EditCurrencyCommand(3L, "USD", "USD", 1);
        // Try to update duplicated currency code
        assertThrows(ApplicationException.class, () -> editCurrencyUseCase.execute(command));
    }
}
