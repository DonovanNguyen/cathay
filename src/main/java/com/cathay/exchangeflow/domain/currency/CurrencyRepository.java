package com.cathay.exchangeflow.domain.currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {

    List<Currency> findAll();

    void add(Currency currency);

    void edit(Currency currency);

    Optional<Currency> findById(long id);

    void deleteById(Long id);
}
