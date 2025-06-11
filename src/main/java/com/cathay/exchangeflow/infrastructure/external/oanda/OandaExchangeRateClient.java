package com.cathay.exchangeflow.infrastructure.external.oanda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class OandaExchangeRateClient {

    private static final Logger logger = LoggerFactory.getLogger(OandaExchangeRateClient.class);

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final RestTemplate restTemplate;
    private final OandaProperties oandaProperties;


    public OandaExchangeRateClient(RestTemplate restTemplate, OandaProperties oandaProperties) {
        this.restTemplate = restTemplate;
        this.oandaProperties = oandaProperties;
    }

    public Optional<RetrieveExchangeRateDto> getExchangeRateByDate(String base, String quote,
            LocalDate date) {
        return getExchangeRateByDateRange(base, quote, date, date).stream().findFirst();
    }

    public List<RetrieveExchangeRateDto> getExchangeRateByDateRange(String base, String quote,
            LocalDate startDate, LocalDate endDate) {
        String url = String.format(oandaProperties.getExchangeRateUrl(), base, quote, startDate,
                endDate);
        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode body = response.getBody();
            if (response.getStatusCode().is2xxSuccessful() && body != null) {
                JsonNode responseNode = body.path("response");
                if (responseNode.isArray()) {
                    return StreamSupport.stream(responseNode.spliterator(), false)
                            .map(this::mapJsonToDto).filter(Objects::nonNull).toList();
                }
            }
        } catch (HttpClientErrorException e) {
            logger.error("Failed to fetch exchange rates: {} - {}", e.getStatusCode(),
                    e.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("Unexpected error fetching exchange rates: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    private RetrieveExchangeRateDto mapJsonToDto(JsonNode item) {
        try {
            RetrieveExchangeRateDto dto = new RetrieveExchangeRateDto();
            dto.setBaseCurrency(getTextValue(item, "base_currency"));
            dto.setQuoteCurrency(getTextValue(item, "quote_currency"));

            String closeTime = getTextValue(item, "close_time");
            // Parse the close time to LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(closeTime, DATE_TIME_FORMATTER);
            dto.setDateTime(dateTime);

            dto.setAverageBid(getBigDecimalValue(item, "average_bid"));
            dto.setAverageAsk(getBigDecimalValue(item, "average_ask"));
            dto.setHighBid(getBigDecimalValue(item, "high_bid"));
            dto.setHighAsk(getBigDecimalValue(item, "high_ask"));
            dto.setLowBid(getBigDecimalValue(item, "low_bid"));
            dto.setLowAsk(getBigDecimalValue(item, "low_ask"));

            return dto;
        } catch (Exception e) {
            logger.error("Failed to map JSON. Item: {}. Error: {}", item, e.getMessage(), e);
            return null;
        }
    }

    private String getTextValue(JsonNode node, String fieldName) {
        JsonNode valueNode = node.get(fieldName);
        return (valueNode != null && !valueNode.isNull()) ? valueNode.asText() : null;
    }

    private BigDecimal getBigDecimalValue(JsonNode node, String fieldName) {
        String text = getTextValue(node, fieldName);
        try {
            return text != null ? new BigDecimal(text) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
