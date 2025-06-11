CREATE TABLE currencies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    version INT NOT NULL DEFAULT 0
);

CREATE TABLE exchange_rates (
    base_currency VARCHAR(3) NOT NULL,
    quote_currency VARCHAR(3) NOT NULL,
    rate_date DATE NOT NULL,
    rate DECIMAL(10, 2),

    CONSTRAINT pk_exchange_rate PRIMARY KEY (base_currency, quote_currency, rate_date),
    CONSTRAINT fk_base_currency FOREIGN KEY (base_currency) REFERENCES currencies(code),
    CONSTRAINT fk_quote_currency FOREIGN KEY (quote_currency) REFERENCES currencies(code)
);
