package com.cathay.exchangeflow.application.currency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cathay.exchangeflow.application.exception.ApplicationException;
import com.cathay.exchangeflow.core.Version;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyCode;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

class AddCurrencyValidatorTest {
    private CurrencyRepository currencyRepository;
    private AddCurrencyValidator addCurrencyValidator;

    @BeforeEach
    void setUp() {
        currencyRepository = mock(CurrencyRepository.class);
        addCurrencyValidator = new AddCurrencyValidator(currencyRepository);
    }

    @Test
    void testValidate_whenUniqueCode_shouldNotThrow() {
        Currency newCurrency =
                new Currency(3L, CurrencyCode.of("GBP"), "British Pound", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));
        when(currencyRepository.findAll()).thenReturn(existing);

        assertDoesNotThrow(() -> addCurrencyValidator.validate(newCurrency));
    }

    @Test
    void testValidate_whenDuplicatedCode_shouldThrowApplicationException() {
        Currency newCurrency = new Currency(3L, CurrencyCode.of("USD"), "US Dollar", Version.of(1));
        List<Currency> existing =
                List.of(new Currency(1L, CurrencyCode.of("USD"), "US Dollar", Version.of(2)),
                        new Currency(2L, CurrencyCode.of("EUR"), "Euro", Version.of(3)));
        when(currencyRepository.findAll()).thenReturn(existing);

        ApplicationException ex = assertThrows(ApplicationException.class,
                () -> addCurrencyValidator.validate(newCurrency));
        assertEquals("currency.code.duplicated", ex.getMessageCodes().get(0).value());
    }
}
