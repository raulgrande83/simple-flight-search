package com.lastminute.flightsearch.data;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.mocks.MockData;

public class FlightsDataTest {

	@Before
	public void createFlightData(){
		MockData.generateMocks();
	}
	
	@After
	public void deleteFlightData(){
		MockData.deleteMocks();
	}
	
	@Test
	public void testGetAirportsList() {
		assertTrue("The airports list is not empty.", !FlightsData.getAirportsList().isEmpty());
	}

	@Test
	public void testGetAirlinesList() {
		assertTrue("The airlines list is not empty.", !FlightsData.getAirlinesList().isEmpty());
	}

	@Test
	public void testGetFlightsList() {
		assertTrue("The flights list is not empty.", !FlightsData.getFlightsList().isEmpty());
	}

}
