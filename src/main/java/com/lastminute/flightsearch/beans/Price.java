package com.lastminute.flightsearch.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * This class is used to represent the amount and currency of prices
 * @author raulgrande83
 *
 */
public class Price {

	//By default the currency used is EURO
    private static final Currency EURO = Currency.getInstance("EUR");
    //The rounding mode
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    //Stores the amount of the price
    private BigDecimal amount;
    //Stores the currency of the price
    private Currency currency;   

    /**
     * Constructor receiving the amount of the price
     * @param amount
     */
    public Price (BigDecimal amount) {
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

    /**
     * Method that prints in format 000.00 €
     */
    @Override
    public String toString() {
        return getAmount() + " " + getCurrency().getSymbol();
    }
}