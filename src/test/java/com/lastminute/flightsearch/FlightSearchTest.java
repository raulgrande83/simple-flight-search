package com.lastminute.flightsearch;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.exceptions.SearchParamsException;
import com.lastminute.flightsearch.mocks.MockData;

public class FlightSearchTest {
	
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
	public void testSearchAMSFRA1Adult31Days() throws SearchParamsException {
		final String departure = getStringDatePlusDays(31);
		final String origin = "AMS";
		final String destination = "FRA";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be 3 flights available", 3, results.size());
		assertEquals("First flight should be TK2372", "TK2372", results.get(0).getFlightNumber());
		assertEquals("First flight price should be 157.60", "157.60", results.get(0).getTotalPrice().getAmount().toString());
		assertEquals("Second flight should be TK2659", "TK2659", results.get(1).getFlightNumber());
		assertEquals("Second flight price should be 198.40", "198.40", results.get(1).getTotalPrice().getAmount().toString());
		assertEquals("Third flight should be LH5909", "LH5909", results.get(2).getFlightNumber());
		assertEquals("Third flight price should be 90.40", "90.40", results.get(2).getTotalPrice().getAmount().toString());
	}
	
	@Test
	public void testSearchLHRIST2Adults1Child1Infant15Days() throws SearchParamsException {
		final String departure = getStringDatePlusDays(15);
		final String origin = "LHR";
		final String destination = "IST";
		final int numberAdults = 2;
		final int numberChildren = 1;
		final int numberInfant = 1;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be 2 flights available", 2, results.size());
		assertEquals("First flight should be TK8891", "TK8891", results.get(0).getFlightNumber());
		assertEquals("First flight price should be 806.00", "806.00", results.get(0).getTotalPrice().getAmount().toString());
		assertEquals("Second flight should be LH1085", "LH1085", results.get(1).getFlightNumber());
		assertEquals("Second flight price should be 481.19", "481.19", results.get(1).getTotalPrice().getAmount().toString());
	}
	
	@Test
	public void testSearchBCNMAD1Adult2Children2Days() throws SearchParamsException {
		final String departure = getStringDatePlusDays(2);
		final String origin = "BCN";
		final String destination = "MAD";
		final int numberAdults = 1;
		final int numberChildren = 2;
		final int numberInfant = 0;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be 2 flights available", 2, results.size());
		assertEquals("First flight should be IB2171", "IB2171", results.get(0).getFlightNumber());
		assertEquals("First flight price should be 909.09", "909.09", results.get(0).getTotalPrice().getAmount().toString());
		assertEquals("Second flight should be LH5496", "LH5496", results.get(1).getFlightNumber());
		assertEquals("Second flight price should be 1028.43", "1028.43", results.get(1).getTotalPrice().getAmount().toString());
	}
	
	@Test
	public void testSearchCDGFRA1Adult2Days() throws SearchParamsException {
		final String departure = getStringDatePlusDays(2);
		final String origin = "CDG";
		final String destination = "FRA";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be no flights available", 0, results.size());
	}
	
	@Test
	public void testSearchFakeAirports() throws SearchParamsException {
		final String departure = getStringDatePlusDays(10);
		final String origin = "AAA";
		final String destination = "BBB";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be no flights available", 0, results.size());
	}
	
	@Test
	public void testSearchNoPassengers() throws SearchParamsException{
		final String departure = getStringDatePlusDays(31);
		final String origin = "AMS";
		final String destination = "FRA";
		
		exception.expect(SearchParamsException.class);
		exception.expectMessage("The total number of passengers must be more than 0.");
		FlightSearch.doFlightSearch(origin, destination, departure, 0, 0, 0);
	}
	
	@Test
	public void testSearchNegativePassengers() throws SearchParamsException{
		final String departure = getStringDatePlusDays(31);
		final String origin = "AMS";
		final String destination = "FRA";
		
		exception.expect(SearchParamsException.class);
		exception.expectMessage("The number of adult passengers must be 0 or more.");
		exception.expectMessage("The number of infant passengers must be 0 or more.");
		exception.expectMessage("The number of child passengers must be 0 or more.");
		FlightSearch.doFlightSearch(origin, destination, departure, -1, -1, -1);
	}
	
	@Test
	public void testSearchNullOriginDestination() throws SearchParamsException{
		final String departure = getStringDatePlusDays(31);
		final String origin = null;
		final String destination = "";
		
		exception.expect(SearchParamsException.class);
		exception.expectMessage("The origin must be informed.");
		exception.expectMessage("The destination must be informed.");
		FlightSearch.doFlightSearch(origin, destination, departure, 1, 0, 0);
	}
	
	@Test
	public void testSearchBadDate() throws SearchParamsException {
		final String departure = "12345678AAA";
		final String origin = "AMS";
		final String destination = "FRA";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		exception.expect(SearchParamsException.class);
		exception.expectMessage("The format of the date is not valid.");
		FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
	}
	
	@Test
	public void testSearchPreviousDate() throws SearchParamsException {
		final String departure = getStringDatePlusDays(-1);
		final String origin = "AMS";
		final String destination = "FRA";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		exception.expect(SearchParamsException.class);
		exception.expectMessage("The departure date must be greater than today.");
		FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
	}
	
	/**
	 * Method to get a String with the number of days added to today
	 * @param numberOfDays The number of days to be added
	 * @return A String with the date in format dd/MM/yyyy
	 */
	private static String getStringDatePlusDays(int numberOfDays){
		Date departureDate = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);
		cal.add(Calendar.DATE, numberOfDays); // add numberOfDays
		departureDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		return df.format(departureDate);
	}

}
