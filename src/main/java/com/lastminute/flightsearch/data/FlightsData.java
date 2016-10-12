package com.lastminute.flightsearch.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;

public class FlightsData {

	private static List<Airport> airportsList = new ArrayList<Airport>();
	private static List<Airline> airlinesList = new ArrayList<Airline>();
	private static List<Flight> flightsList = new ArrayList<Flight>();

	public static List<Airport> getAirportsList() {
		if(airportsList.isEmpty()){
//			airportsList.add(new Airport("MAD", "Madrid"));
//			airportsList.add(new Airport("BCN", "Barcelona"));
//			airportsList.add(new Airport("LHR", "London"));
//			airportsList.add(new Airport("CDG", "Paris"));
//			airportsList.add(new Airport("FRA", "Frakfurt"));
//			airportsList.add(new Airport("IST", "Istanbul"));
//			airportsList.add(new Airport("AMS", "Amsterdam"));
//			airportsList.add(new Airport("FCO", "Rome"));
//			airportsList.add(new Airport("CPH", "Copenhagen"));
			
			try {
				airportsList = DataFileReader.getAirportData();
			} catch (FileNotFoundException e) {
				System.err.println("The airports data could not be retrieved");
			}
		}
		return airportsList;
	}

	public static List<Airline> getAirlinesList() {
		if(airlinesList.isEmpty()){
			try {
				airlinesList = DataFileReader.getAirlinesData();
			} catch (FileNotFoundException e) {
				System.err.println("The airlines data could not be retrieved");
			}
		}
		return airlinesList;
	}

	public static List<Flight> getFlightsList() {
		
		if(flightsList.isEmpty()){
			try {
				flightsList = DataFileReader.getFlightsData();
			} catch (FileNotFoundException e) {
				System.err.println("The flights data could not be retrieved");
			}
		}
		return flightsList;
	}
	
	
	
	
}
