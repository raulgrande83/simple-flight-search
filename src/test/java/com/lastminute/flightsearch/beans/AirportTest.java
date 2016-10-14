package com.lastminute.flightsearch.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AirportTest {

	private static final String IATA_CODE = "AAA";
	private static final String CITY = "Dummy City";
	private static Airport airport;
	private static Airport airportNull;
	
	@Before
	/**
	 * Method that builds different test data
	 */
	public void buildAirports(){
		airport = new Airport(IATA_CODE, CITY);
		airportNull = new Airport(null, null);
	}
	
	@After
	/**
	 * Method that cleans the created test data
	 */
	public void cleanAirports(){
		airport = null;
		airportNull = null;
	}
	
	@Test
	public void testAirportConstructor() {
		assertNotNull("The airport should not be null", airport);
	}
	
	@Test
	public void testAirportConstructorEmpty() {
		assertNotNull("The airport should not be null", airportNull);
	}

	@Test
	public void testGetIATACodeDummy() {
		assertEquals("The IATA Code should be "+IATA_CODE, IATA_CODE, airport.getIATACode());
		assertNull("The IATA Code should be null", airportNull.getIATACode());
		
	}

	@Test
	public void testGetCityDummy() {
		assertEquals("The City name should be "+CITY, CITY, airport.getCity());
	}

}
