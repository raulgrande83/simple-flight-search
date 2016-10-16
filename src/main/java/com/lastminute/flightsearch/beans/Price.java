package com.lastminute.flightsearch.beans;

import java.math.BigDecimal;
import java.util.Currency;

import com.lastminute.flightsearch.constants.Constants;

/**
 * This class is used to represent the amount and currency of prices
 * @author raulgrande83
 *
 */
public class Price {

    //Stores the amount of the price
    private BigDecimal amount;
    //Stores the currency of the price
    private Currency currency;   

    /**
     * Constructor receiving the amount of the price
     * @param amount
     */
    public Price (BigDecimal amount) {
    	this.currency = Constants.EURO;
    	if(amount!=null){
	    	this.amount = amount;	
	    	this.amount = amount.setScale(2, Constants.DEFAULT_ROUNDING);
    	}
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    /**
     * Method that prints in format 000.00 �
     */
    @Override
    public String toString() {
        return getAmount() + " " + getCurrency().getSymbol();
    }
}