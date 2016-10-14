package com.lastminute.flightsearch.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {

    private static final Currency EURO = Currency.getInstance("EUR");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private BigDecimal amount;
    private Currency currency;   

    public Money (BigDecimal amount) {
    	this.amount = amount;
    	this.currency = EURO;
    	this.amount = amount.setScale(2, DEFAULT_ROUNDING);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return getAmount() + " " + getCurrency().getSymbol();
    }
}