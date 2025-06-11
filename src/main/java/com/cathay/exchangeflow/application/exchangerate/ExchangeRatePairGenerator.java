package com.cathay.exchangeflow.application.exchangerate;

import java.util.ArrayList;
import java.util.List;
import com.cathay.exchangeflow.domain.currency.Currency;
import com.cathay.exchangeflow.domain.exchangerate.ExchangeRatePair;

public class ExchangeRatePairGenerator {

    /**
     * Generates all possible ordered currency pairs (excluding self-pairs) from the given list of
     * currencies.
     * <p>
     * For example, given currencies USD, AUD, JPY, this method will return:
     * <ul>
     * <li>USD/AUD</li>
     * <li>USD/JPY</li>
     * <li>AUD/USD</li>
     * <li>AUD/JPY</li>
     * <li>JPY/USD</li>
     * <li>JPY/AUD</li>
     * </ul>
     *
     * @param currencies the list of {@link Currency} objects to generate pairs from
     * @return a list of {@link ExchangeRatePair} representing all possible ordered pairs (base,
     *         quote)
     */
    public static List<ExchangeRatePair> generate(List<Currency> currencies) {
        List<ExchangeRatePair> pairs = new ArrayList<>();
        for (int i = 0; i < currencies.size(); i++) {
            for (int j = 0; j < currencies.size(); j++) {
                if (i != j) {
                    pairs.add(new ExchangeRatePair(currencies.get(i).getCode().getValue(),
                            currencies.get(j).getCode().getValue()));
                }
            }
        }
        return pairs;
    }
}
