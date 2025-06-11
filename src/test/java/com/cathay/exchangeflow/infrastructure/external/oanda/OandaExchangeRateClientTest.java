package com.cathay.exchangeflow.infrastructure.external.oanda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class OandaExchangeRateClientTest {
    @Test
    void testGetExchangeRateByDateRange_success() throws Exception {
        // Given:
        RestTemplate restTemplate = mock(RestTemplate.class);
        OandaProperties oandaProperties = mock(OandaProperties.class);
        ObjectMapper mapper = new ObjectMapper();

        String base = "USD";
        String quote = "EUR";
        LocalDate startDate = LocalDate.of(2025, 6, 10);
        LocalDate endDate = LocalDate.of(2025, 6, 10);

        String url = "http://fake-url";
        when(oandaProperties.getExchangeRateUrl()).thenReturn("%s");
        // The url is not important for the test, just needs to match the format
        String oandaResponse = """
                {
                  "response": [
                    {
                      "base_currency": "USD",
                      "quote_currency": "EUR",
                      "close_time": "2025-06-10T12:00:00",
                      "average_bid": "1.10",
                      "average_ask": "1.12",
                      "high_bid": "1.15",
                      "high_ask": "1.16",
                      "low_bid": "1.08",
                      "low_ask": "1.09"
                    }
                  ]
                }
                """;
        when(restTemplate.getForEntity(anyString(), eq(JsonNode.class)))
                .thenReturn(new ResponseEntity<>(mapper.readTree(oandaResponse), HttpStatus.OK));

        OandaExchangeRateClient client = new OandaExchangeRateClient(restTemplate, oandaProperties);

        // When:
        List<RetrieveExchangeRateDto> result =
                client.getExchangeRateByDateRange(base, quote, startDate, endDate);

        // Then:
        assertNotNull(result);
        assertEquals(1, result.size());
        RetrieveExchangeRateDto dto = result.get(0);
        assertEquals("USD", dto.getBaseCurrency());
        assertEquals("EUR", dto.getQuoteCurrency());
        assertEquals(LocalDateTime.of(2025, 6, 10, 12, 0), dto.getDateTime());
        assertEquals(new BigDecimal("1.10"), dto.getAverageBid());
        assertEquals(new BigDecimal("1.12"), dto.getAverageAsk());
        assertEquals(new BigDecimal("1.15"), dto.getHighBid());
        assertEquals(new BigDecimal("1.16"), dto.getHighAsk());
        assertEquals(new BigDecimal("1.08"), dto.getLowBid());
        assertEquals(new BigDecimal("1.09"), dto.getLowAsk());
    }

    @Test
    void testGetExchangeRateByDateRange_emptyResponse() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        OandaProperties oandaProperties = mock(OandaProperties.class);

        when(oandaProperties.getExchangeRateUrl()).thenReturn("%s");
        when(restTemplate.getForEntity(anyString(), eq(JsonNode.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        OandaExchangeRateClient client = new OandaExchangeRateClient(restTemplate, oandaProperties);

        List<RetrieveExchangeRateDto> result =
                client.getExchangeRateByDateRange("USD", "EUR", LocalDate.now(), LocalDate.now());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetExchangeRateByDateRange_httpClientError() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        OandaProperties oandaProperties = mock(OandaProperties.class);

        when(oandaProperties.getExchangeRateUrl()).thenReturn("%s");
        when(restTemplate.getForEntity(anyString(), eq(JsonNode.class))).thenThrow(
                new org.springframework.web.client.HttpClientErrorException(HttpStatus.NOT_FOUND));

        OandaExchangeRateClient client = new OandaExchangeRateClient(restTemplate, oandaProperties);

        List<RetrieveExchangeRateDto> result =
                client.getExchangeRateByDateRange("USD", "EUR", LocalDate.now(), LocalDate.now());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
