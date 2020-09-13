package com.IBMtask.currencyConverter.Conversion;

import java.math.BigDecimal;

public class ConversionCurrency {

    private String to;
    private String from;

    private BigDecimal value;

    public ConversionCurrency() {
    }

    public ConversionCurrency(String to, String from, BigDecimal value) {
        this.to = to;
        this.from = from;
        this.value = value;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
