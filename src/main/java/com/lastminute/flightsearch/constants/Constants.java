package com.lastminute.flightsearch.constants;

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
	
}
