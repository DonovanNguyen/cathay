package com.cathay.exchangeflow.infrastructure.persistence.exchangerate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaExchangeRateRepository
                extends JpaRepository<ExchangeRateDto, ExchangeRateDto.ExchangeRateId> {

}
