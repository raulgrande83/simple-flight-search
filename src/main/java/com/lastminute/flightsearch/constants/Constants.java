package com.lastminute.flightsearch.constants;

import java.math.RoundingMode;
import java.util.Currency;

/**
 * Class that contains the constants used
 * @author raulgrande83
 *
 */
public class Constants {

	//Where the data files are stored
	public static final String DATA_LOCATION = "./src/main/resources/data/";
	//These files store the data about airports, airlines and flights
	public static final String DATA_AIRPORTS = DATA_LOCATION+"airports.csv";
	public static final String DATA_AIRLINES = DATA_LOCATION+"airlines.csv";
	public static final String DATA_FLIGHTS = DATA_LOCATION+"flights.csv";
	
	//The delimiter defined for CSV files
	public static final String DATA_DELIMITER = ",";
	
	//Number of digits of IATACode in Flight Numbers
	public static final int IATA_DIGITS = 2;
	
	//The format to receive dates
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	//By default the currency used is EURO
    public static final Currency EURO = Currency.getInstance("EUR");
    //The rounding mode
    public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
}
