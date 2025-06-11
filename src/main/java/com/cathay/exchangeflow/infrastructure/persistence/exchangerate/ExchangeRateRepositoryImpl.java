package com.cathay.exchangeflow.infrastructure.persistence.exchangerate;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRate;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRateRepository;

@Repository
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private final JpaExchangeRateRepository jpaExchangeRateRepository;


    public ExchangeRateRepositoryImpl(JpaExchangeRateRepository jpaExchangeRateRepository) {
        this.jpaExchangeRateRepository = jpaExchangeRateRepository;
    }

    @Override
    public void saveAll(List<ExchangeRate> rates) {
        List<ExchangeRateDto> dtos =
                rates.stream().map(r -> new ExchangeRateDto(r.getBaseCurrency(),
                        r.getQuoteCurrency(), r.getDateTime(), r.getRate().getValue())).toList();
        jpaExchangeRateRepository.saveAll(dtos);
    }

}
