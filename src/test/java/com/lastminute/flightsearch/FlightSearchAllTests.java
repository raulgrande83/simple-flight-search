package com.lastminute.flightsearch;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.lastminute.flightsearch.beans.AirportTest;
import com.lastminute.flightsearch.data.FlightsDataTest;
import com.lastminute.flightsearch.pricerules.DaysPriceRulesTest;
import com.lastminute.flightsearch.pricerules.PassengerPriceRulesTest;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		//Test beans
		AirportTest.class,
		//Test data objects
		FlightsDataTest.class,
		//Test price rules enumerations
		DaysPriceRulesTest.class,
		PassengerPriceRulesTest.class,
		//Test search logic
		FlightSearchTest.class 
	})

/**
 * This class runs all tests in the application
 * @author raulgrande83
 *
 */
public class FlightSearchAllTests {}
