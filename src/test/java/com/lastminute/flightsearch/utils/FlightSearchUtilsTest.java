package com.lastminute.flightsearch.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.mocks.MockData;
import com.lastminute.flightsearch.pricerules.DaysPriceRules;

public class FlightSearchUtilsTest {

	private static final Airline airline = new Airline("IB", "Iberia", new BigDecimal(10));
	private static final Airport airport = new Airport("MAD", "Madrid"); 
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void createFlightData(){
		MockData.generateMocks();
	}
	
	@After
	public void deleteFlightData(){
		MockData.deleteMocks();
	}
	
	@Test
	public void testGetAirlineByIATAExists() {
		final Airline resultAirline = FlightSearchUtils.getAirlineByIATA(airline.getIATACode());
		assertEquals("The airline IATA Code should be "+airline.getIATACode(), airline.getIATACode(), resultAirline.getIATACode());
		assertEquals("The airline Name should be "+airline.getName(), airline.getName(), resultAirline.getName());
		assertEquals("The airline Infant Price should be "+airline.getInfantPrice().getAmount(), airline.getInfantPrice().getAmount(), resultAirline.getInfantPrice().getAmount());
	}
	
	@Test
	public void testGetAirlineByIATADontExists() {
		final Airline resultAirline = FlightSearchUtils.getAirlineByIATA("_AZ_");
		assertNull("The airline should be null", resultAirline);
	}
	
	@Test
	public void testGetAirportByIATAExists() {
		final Airport resultAirport = FlightSearchUtils.getAirportByIATA(airport.getIATACode());
		assertEquals("The airport IATA Code should be "+airport.getIATACode(), airport.getIATACode(), resultAirport.getIATACode());
		assertEquals("The airport Name should be "+airport.getCity(), airport.getCity(), resultAirport.getCity());
	}
	
	@Test
	public void testGetAirportByIATADontExists() {
		final Airport resultAirport = FlightSearchUtils.getAirportByIATA("_AZ_");
		assertNull("The airport should be null", resultAirport);
	}


	@Test
	public void testGetFlightsOriginDestination() {
		final List<Flight> resultFlights = FlightSearchUtils.getFlightsOriginDestination("BCN", "MAD");
		assertNotNull("The flights list should not be null", resultFlights);
	}

	@Test
	public void testGetAirlineFromFlightNumber() {
		final Airline resultAirline = FlightSearchUtils.getAirlineFromFlightNumber("IB1234");
		assertEquals("The airline IATA Code should be "+airline.getIATACode(), airline.getIATACode(), resultAirline.getIATACode());
		assertEquals("The airline Name should be "+airline.getName(), airline.getName(), resultAirline.getName());
		assertEquals("The airline Infant Price should be "+airline.getInfantPrice().getAmount(), airline.getInfantPrice().getAmount(), resultAirline.getInfantPrice().getAmount());
	}

	@Test
	public void testGetFlightDate() throws ParseException {
		final Calendar flightDate = FlightSearchUtils.getFlightDate("24/01/2017");
		assertNotNull("The date should not be null", flightDate);
		assertEquals("The day should be 24", 24, flightDate.get(Calendar.DAY_OF_MONTH));
		//Calendar.MONTH returns the number of month-1
		assertEquals("The month should be 1", 1, flightDate.get(Calendar.MONTH)+1);
		assertEquals("The year should be 2017", 2017, flightDate.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetFlightDateWrongFormat() throws ParseException {
		exception.expect(ParseException.class);
		FlightSearchUtils.getFlightDate("24_01_2017");
	}

	@Test
	public void testCalculateDepartureDays() {
		//Add 5 days
		Calendar departureDate = addDaysToCalendar(5);
		final long days = FlightSearchUtils.calculateDepartureDays(departureDate);
		assertEquals("The days between departure date and today should be 5", 5, days);		
	}

	@Test
	public void testGetPriceRule1() {
		Calendar departureDate = addDaysToCalendar(31);
		final DaysPriceRules rule = FlightSearchUtils.getPriceRule(departureDate);
		assertEquals("The Price Rule should be "+DaysPriceRules.RULE_1, DaysPriceRules.RULE_1, rule);
	}
	
	@Test
	public void testGetPriceRule2() {
		Calendar departureDate = addDaysToCalendar(16);
		final DaysPriceRules rule = FlightSearchUtils.getPriceRule(departureDate);
		assertEquals("The Price Rule should be "+DaysPriceRules.RULE_2, DaysPriceRules.RULE_2, rule);
	}
	
	@Test
	public void testGetPriceRule3() {
		Calendar departureDate = addDaysToCalendar(14);
		final DaysPriceRules rule = FlightSearchUtils.getPriceRule(departureDate);
		assertEquals("The Price Rule should be "+DaysPriceRules.RULE_3, DaysPriceRules.RULE_3, rule);
	}
	
	@Test
	public void testGetPriceRule4() {
		Calendar departureDate = addDaysToCalendar(1);
		final DaysPriceRules rule = FlightSearchUtils.getPriceRule(departureDate);
		assertEquals("The Price Rule should be "+DaysPriceRules.RULE_4, DaysPriceRules.RULE_4, rule);
	}
	
	/**
	 * Returns a calendar with the number of days added
	 * @param numberOfDays The days to be added
	 * @return Calendar The Calendar object
	 */
	private static Calendar addDaysToCalendar(int numberOfDays){
		Calendar departureDate = Calendar.getInstance();
		int departureDays = departureDate.get(Calendar.DAY_OF_YEAR);
		departureDays += numberOfDays;
		departureDate.set(Calendar.DAY_OF_YEAR, departureDays);
		return departureDate;
	}

}

