package com.cathay.exchangeflow.domain.exchangerate;

import java.util.List;

public interface ExchangeRateRepository {

    void saveAll(List<ExchangeRate> rates);

}
