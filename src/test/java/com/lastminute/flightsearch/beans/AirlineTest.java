package com.lastminute.flightsearch.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.constants.Constants;

public class AirlineTest {

	private static final String IATA_CODE = "AA";
	private static final String NAME = "AAAA Airlines";
	private static final BigDecimal INFANT_PRICE = new BigDecimal(99.99).setScale(2, Constants.DEFAULT_ROUNDING);
	
	private static Airline airline;
	private static Airline airlineNull;

	/**
	 * Method that builds different test data
	 */
	@Before
	public void buildAirlines(){
		airline = new Airline(IATA_CODE, NAME, INFANT_PRICE);
		airlineNull = new Airline(null, null, null);
	}
	
	/**
	 * Method that cleans the created test data
	 */
	@After
	public void cleanAirports(){
		airline = null;
		airlineNull = null;
	}
	
	@Test
	public void testAirlineConstructor() {
		assertNotNull("The airline should not be null", airline);
	}
	
	@Test
	public void testAirlineConstructorEmpty() {
		assertNotNull("The airline should not be null", airlineNull);
	}
	
	@Test
	public void testGetIATACodeDummy() {
		assertEquals("The IATA Code should be "+IATA_CODE, IATA_CODE, airline.getIATACode());		
	}

	@Test
	public void testGetIATACodeNull(){
		assertNull("The IATA Code should be null", airlineNull.getIATACode());
	}
	
	@Test
	public void testGetNameDummy() {
		assertEquals("The Name should be "+NAME, NAME, airline.getName());		
	}

	@Test
	public void testGetNameNull(){
		assertNull("The Name should be null", airlineNull.getName());
	}
	
	@Test
	public void testGetPriceDummy() {
		assertEquals("The Infant Price should be "+INFANT_PRICE, INFANT_PRICE, airline.getInfantPrice().getAmount());		
	}

	@Test
	public void testGetPriceNull(){
		assertNull("The Infant Price should be null", airlineNull.getInfantPrice().getAmount());
	}
	
}
