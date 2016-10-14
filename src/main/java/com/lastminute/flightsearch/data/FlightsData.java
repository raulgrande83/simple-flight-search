package com.lastminute.flightsearch.data;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;

/**
 * This class stores the information about airports, airlines and flights
 * @author raulgrande83
 *
 */
public class FlightsData {

	//The airports list
	private static List<Airport> airportsList = new ArrayList<Airport>();
	//The airlines list
	private static List<Airline> airlinesList = new ArrayList<Airline>();
	//The flights list
	private static List<Flight> flightsList = new ArrayList<Flight>();

	/**
	 * Method that gets the airports list.
	 * @return List<Airport> The airports list.
	 */
	public static List<Airport> getAirportsList() {
		//Only if the airports list is empty, read the file
		if(airportsList.isEmpty()){
			try {
				//Retrieve the data from the file
				airportsList = DataFileReader.getAirportData();
			} catch (FileNotFoundException e) {
				System.err.println("The airports data could not be retrieved");
			}
		}
		return airportsList;
	}

	/**
	 * Method that gets the airlines list.
	 * @return List<Airline> The airlines list.
	 */
	public static List<Airline> getAirlinesList() {
		//Only if the airlines list is empty, read the file
		if(airlinesList.isEmpty()){
			try {
				//Retrieve the data from the file
				airlinesList = DataFileReader.getAirlinesData();
			} catch (FileNotFoundException e) {
				System.err.println("The airlines data could not be retrieved");
			}
		}
		return airlinesList;
	}

	/**
	 * Method that gets the flights list.
	 * @return List<Flight> The flights list.
	 */
	public static List<Flight> getFlightsList() {
		//Only if the airlines list is empty, read the file
		if(flightsList.isEmpty()){
			try {
				//Retrieve the data from the file
				flightsList = DataFileReader.getFlightsData();
			} catch (FileNotFoundException e) {
				System.err.println("The flights data could not be retrieved");
			}
		}
		return flightsList;
	}

	public static void setAirportsList(List<Airport> airportsList) {
		FlightsData.airportsList = airportsList;
	}

	public static void setAirlinesList(List<Airline> airlinesList) {
		FlightsData.airlinesList = airlinesList;
	}

	public static void setFlightsList(List<Flight> flightsList) {
		FlightsData.flightsList = flightsList;
	}
}
