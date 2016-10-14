package com.lastminute.flightsearch.pricerules;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaysPriceRulesTest {

	
	@Test
	public void testRulesDays() {
		for(DaysPriceRules priceRule: DaysPriceRules.values()){
			assertTrue("The price rule should not have a minDay greater than maxDay. Price Rule: "+priceRule, priceRule.getDaysMin() < priceRule.getDaysMax());
		}
	}
	
	@Test
	public void testRulesNotNull(){
		for(DaysPriceRules priceRule: DaysPriceRules.values()){
			assertNotNull("The price rule should not be null. Price Rule: "+priceRule, priceRule);
		}
	}
	
	@Test
	public void testRulesPercentage() {
		for(DaysPriceRules priceRule: DaysPriceRules.values()){
			assertTrue("The price rule should have a percentage > 0. Price Rule: "+priceRule, priceRule.getPercentage() > 0);
		}
	}
}
