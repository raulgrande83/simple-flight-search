package com.lastminute.flightsearch.pricerules;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerPriceRulesTest {

	@Test
	public void testRulesNotNull(){
		for(PassengerPriceRules priceRule: PassengerPriceRules.values()){
			assertNotNull("The passenger price rule should not be null. Price rule: "+priceRule, priceRule);
		}
	}

	@Test
	public void testRulesDiscount(){
		for(PassengerPriceRules priceRule: PassengerPriceRules.values()){
			assertTrue("The discount of the price rule should be between 0 and 1. Price rule: "+priceRule, priceRule.getDiscount()>=0 && priceRule.getDiscount()<=1);
		}
	}
}
