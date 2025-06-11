package com.cathay.exchangeflow.infrastructure.persistence.currency;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.currency.CurrencyRepository;

@Repository
public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final JpaCurrencyRepository jpaCurrencyRepository;

    public CurrencyRepositoryImpl(JpaCurrencyRepository jpaCurrencyRepository) {
        this.jpaCurrencyRepository = jpaCurrencyRepository;
    }

    @Override
    public List<Currency> findAll() {
        return jpaCurrencyRepository.findAllByOrderByCodeAsc().stream().map(CurrencyDto::toCurrency)
                .toList();
    }

    @Override
    public Optional<Currency> findById(long id) {
        return jpaCurrencyRepository.findById(id).map(CurrencyDto::toCurrency);
    }

    @Override
    public void add(Currency currency) {
        jpaCurrencyRepository.save(new CurrencyDto(currency.getId(), currency.getCode().getValue(),
                currency.getName(), currency.getVersion().getValue()));
    }

    @Override
    public void edit(Currency currency) {
        jpaCurrencyRepository.save(new CurrencyDto(currency.getId(), currency.getCode().getValue(),
                currency.getName(), currency.getVersion().getValue()));
    }

    @Override
    public void deleteById(Long id) {
        jpaCurrencyRepository.deleteById(id);
    }

}
