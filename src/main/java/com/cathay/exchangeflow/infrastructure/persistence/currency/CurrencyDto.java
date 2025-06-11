package com.cathay.exchangeflow.infrastructure.persistence.currency;

import com.cathay.exchangeflow.domain.currency.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "currencies")
public class CurrencyDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String name;
    @Version
    @Column(nullable = false)
    private Integer version;

    public CurrencyDto() {
        // Default constructor for JPA
    }

    public CurrencyDto(Long id, String code, String name, Integer version) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
    }

    Currency toCurrency() {
        return Currency.of(id, code, name, version);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Integer getVersion() {
        return version;
    }
}
