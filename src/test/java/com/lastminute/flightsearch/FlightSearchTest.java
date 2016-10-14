package com.lastminute.flightsearch;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.mocks.MockData;

public class FlightSearchTest {
	
	@Before
	public void createFlightData(){
		MockData.generateMocks();
	}
	
	@After
	public void deleteFlightData(){
		MockData.deleteMocks();
	}

	@Test
	public void testSearchAMSFRA1Adult31Days() {
		final String departure = getStringDatePlusDays(31);
		final String origin = "AMS";
		final String destination = "FRA";
		final int numberAdults = 1;
		final int numberChildren = 0;
		final int numberInfant = 0;
		
		List<Flight> results = FlightSearch.doFlightSearch(origin, destination, departure, numberAdults, numberInfant, numberChildren);
		
		assertEquals("There should be 3 flights available", 3, results.size());
		
	}
	
	private static String getStringDatePlusDays(int numberOfDays){
		Date departureDate = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);
		cal.add(Calendar.DATE, numberOfDays); // add numberOfDays days
		departureDate = cal.getTime();
		
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT);
		
		return df.format(departureDate);
	}

}
