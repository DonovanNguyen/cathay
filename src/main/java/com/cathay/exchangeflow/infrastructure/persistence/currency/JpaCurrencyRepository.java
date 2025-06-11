package com.cathay.exchangeflow.infrastructure.persistence.currency;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaCurrencyRepository extends JpaRepository<CurrencyDto, Long> {

    List<CurrencyDto> findAllByOrderByCodeAsc();
}
