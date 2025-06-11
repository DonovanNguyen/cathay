package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.cathay.exchangeflow.UnitTestSpringBoot;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@UnitTestSpringBoot
class DeleteCurrencyUseCaseTest {

    @Autowired
    private DeleteCurrencyUseCase deleteCurrencyUseCase;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    void testExecute_deletesCurrency() {
        // Assume test-data.sql has a currency with id=1
        assertTrue(currencyRepository.findById(1L).isPresent());

        DeleteCurrencyCommand command = new DeleteCurrencyCommand(1L);
        deleteCurrencyUseCase.execute(command);

        assertFalse(currencyRepository.findById(1L).isPresent());
    }
}
