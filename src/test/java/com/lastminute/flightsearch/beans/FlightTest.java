package com.lastminute.flightsearch.beans;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.data.FlightsData;
import com.lastminute.flightsearch.mocks.MockData;

public class FlightTest {

	private Flight flight = null;
	private Flight flightNull = new Flight();
	
	@Before
	public void createFlightData(){
		MockData.generateMocks();
		if(FlightsData.getFlightsList()!=null && !FlightsData.getFlightsList().isEmpty()){
			flight = FlightsData.getFlightsList().get(0);
		}
	}
	
	@After
	public void deleteFlightData(){
		MockData.deleteMocks();
		flight = null;
	}
	
	@Test
	public void testFlightFields() {
		assertEquals("The flight number should be IB2818", "IB2818", flight.getFlightNumber());
		assertEquals("The airline IATA Code should be IB", "IB", flight.getAirline().getIATACode());
		assertEquals("The airline name should be Iberia", "Iberia", flight.getAirline().getName());
		assertEquals("The airport of origin IATA Code should be CPH", "CPH", flight.getOrigin().getIATACode());
		assertEquals("The airport of origin city name should be Copenhagen", "Copenhagen", flight.getOrigin().getCity());
		assertEquals("The airport of destination IATA Code should be FRA", "FRA", flight.getDestination().getIATACode());
		assertEquals("The airport of destination city name should be Frankfurt", "Frankfurt", flight.getDestination().getCity());
		assertEquals("The base price should be 186.00", new BigDecimal(186.00).setScale(2, Constants.DEFAULT_ROUNDING), flight.getBasePrice().getAmount());
	}
	
	@Test
	public void testFlightFieldsNull() {
		assertNull("The flight number should be null", flightNull.getFlightNumber());
		assertNull("The flight airline should be null", flightNull.getAirline());
		assertNull("The flight origin airport should be null", flightNull.getOrigin());
		assertNull("The flight destination airport should be null", flightNull.getDestination());
		assertNull("The flight base price should be null", flightNull.getBasePrice());
	}

}
