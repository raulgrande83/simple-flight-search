package com.lastminute.flightsearch.beans;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.constants.Constants;

public class PriceTest {

	private static final BigDecimal AMOUNT = new BigDecimal(99.99).setScale(2, Constants.DEFAULT_ROUNDING);
	private Price price = null;
	private Price priceNull = new Price(null);
	
	@Before
	public void createPrice(){
		price = new Price(AMOUNT);
	}
	
	@Test
	public void testFields() {
		assertEquals("The amount of the price should be "+AMOUNT, AMOUNT, price.getAmount());
		assertEquals("The currency of the price should be "+Constants.EURO, Constants.EURO, price.getCurrency());
	}

	@Test
	public void testFieldsNull() {
		assertNull("The amount of the price should be null", priceNull.getAmount());
		assertEquals("The currency of the price should be "+Constants.EURO, Constants.EURO, priceNull.getCurrency());
	}
}
