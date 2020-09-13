CREATE TABLE IF NOT EXISTS  currency_converter_table (
    id INT(10) AUTO_INCREMENT PRIMARY KEY,
    currency VARCHAR(10) NOT NULL,
    exchange_rate DECIMAL(24,5) NOT NULL
);
