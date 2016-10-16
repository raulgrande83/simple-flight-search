package com.lastminute.flightsearch.mocks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.beans.Price;
import com.lastminute.flightsearch.data.FlightsData;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

/**
 * This class generates mock data for testing purposes
 * @author raulgrande83
 *
 */
public class MockData {
	
	private static final String[] ORIGINS = {"CPH", "CPH", "CDG", "BCN", "CPH", "CPH", "FCO", "LHR", "FRA", "CPH", "BCN", "AMS", "FCO", "CPH", "BCN", "MAD", "LHR", "IST", "AMS", "FRA", "MAD", "CPH", "LHR", "MAD", "MAD", "FRA", "BCN", "IST", "IST", "LHR", "FRA", "AMS", "LHR", "FCO", "CPH", "CDG", "CPH", "BCN", "MAD", "AMS", "LHR", "MAD", "AMS", "LHR", "IST", "CPH", "FCO", "AMS", "CPH", "BCN", "FRA", "LHR", "AMS", "FRA", "IST", "IST", "BCN", "IST", "CDG", "LHR", "FCO", "AMS", "IST", "MAD", "FCO", "MAD", "IST", "FRA", "CDG", "CPH", "LHR", "BCN", "CDG", "LHR", "FRA", "IST", "CPH", "LHR", "LHR", "IST", "CDG", "MAD", "FRA", "AMS", "CDG", "FRA", "IST", "MAD", "CDG"};
	private static final String[] DESTINATIONS = {"FRA", "LHR", "MAD", "FRA", "FCO", "FCO", "CDG", "IST", "AMS", "BCN", "IST", "CPH", "MAD", "LHR", "AMS", "CDG", "FRA", "FRA", "FRA", "IST", "CDG", "FRA", "CPH", "LHR", "CDG", "CPH", "MAD", "LHR", "MAD", "LHR", "LHR", "FCO", "IST", "FRA", "MAD", "CPH", "CDG", "LHR", "FCO", "IST", "FCO", "BCN", "FRA", "CDG", "MAD", "AMS", "CDG", "FRA", "BCN", "CPH", "AMS", "BCN", "IST", "LHR", "FCO", "BCN", "BCN", "AMS", "IST", "BCN", "FRA", "CPH", "BCN", "AMS", "AMS", "FRA", "LHR", "MAD", "CPH", "CDG", "AMS", "MAD", "LHR", "AMS", "LHR", "FRA", "IST", "AMS", "FRA", "LHR", "BCN", "LHR", "MAD", "FCO", "LHR", "IST", "CPH", "AMS", "CPH"};
	private static final String[] FLIGHT_NUMBERS = {"IB2818", "U23631", "IB8482", "FR7521", "TK4667", "U24631", "VY4335", "TK8891", "U24107", "U22593", "VY9890", "TK4927", "BA1164", "BA7710", "U24985", "IB9961", "LH2118", "IB8911", "TK2372", "LH4145", "IB6112", "LH1678", "LH6620", "TK4199", "IB7403", "BA4369", "IB2171", "LH6412", "LH1115", "VY8162", "BA8162", "BA7610", "LH1085", "U21423", "U23282", "LH5778", "BA2777", "TK4375", "LH8408", "IB4563", "LH5174", "BA9569", "TK2659", "IB2771", "IB8688", "TK8355", "VY2974", "LH5909", "FR7949", "U27858", "LH2320", "VY4633", "IB7289", "IB9443", "LH4948", "TK5558", "BA9409", "FR9261", "IB7181", "TK1446", "TK2793", "FR1491", "IB9219", "TK7871", "VY4840", "BA8982", "U23526", "BA6773", "IB5257", "LH8545", "IB4737", "LH5496", "U29718", "BA9561", "TK3167", "FR4727", "LH6320", "BA6657", "TK5342", "IB4938", "VY9791", "IB4124", "BA7842", "VY5092", "BA9813", "BA2421", "U28059", "LH7260", "TK2044"};
	private static final double[] PRICES = {186, 152, 295, 150, 137, 268, 158, 250, 237, 218, 178, 290, 118, 138, 191, 128, 165, 180, 197, 169, 112, 298, 217, 186, 253, 109, 259, 197, 160, 285, 205, 168, 148, 274, 113, 263, 284, 208, 149, 109, 251, 232, 248, 289, 150, 137, 111, 113, 176, 237, 288, 149, 163, 254, 176, 211, 215, 267, 227, 217, 175, 284, 279, 159, 260, 171, 254, 157, 299, 230, 110, 293, 103, 253, 118, 108, 115, 122, 295, 226, 289, 272, 121, 178, 171, 226, 262, 191, 186};

	/**
	 * Create mock data for all the lists
	 */
	public static void generateMocks(){
		
		//Create list of airports
		List<Airport> airportsList = new ArrayList<Airport>(9); 
		
		airportsList.add(new Airport("MAD", "Madrid"));
		airportsList.add(new Airport("BCN", "Barcelona"));
		airportsList.add(new Airport("LHR", "London"));
		airportsList.add(new Airport("CDG", "Paris"));
		airportsList.add(new Airport("FRA", "Frankfurt"));
		airportsList.add(new Airport("IST", "Istanbul"));
		airportsList.add(new Airport("AMS", "Amsterdam"));
		airportsList.add(new Airport("FCO", "Rome"));
		airportsList.add(new Airport("CPH", "Copenhagen"));
		
		FlightsData.setAirportsList(airportsList);
		
		//Create list of airlines
		List<Airline> airlinesList = new ArrayList<Airline>();

		airlinesList.add(new Airline("IB", "Iberia"          , new BigDecimal(10)));
		airlinesList.add(new Airline("BA", "British Airways" , new BigDecimal(15)));
		airlinesList.add(new Airline("LH", "Lufthansa"       , new BigDecimal(7)));
		airlinesList.add(new Airline("FR", "Ryanair"         , new BigDecimal(20)));
		airlinesList.add(new Airline("VY", "Vueling"         , new BigDecimal(10)));
		airlinesList.add(new Airline("TK", "Turkish Airlines", new BigDecimal(5)));
		airlinesList.add(new Airline("U2", "Easyjet"         , new BigDecimal(19.90)));
		
		FlightsData.setAirlinesList(airlinesList);
		
		//Create list of flights
		List<Flight> flightsList = new ArrayList<Flight>();
		Flight objFlight = null;
		for(int i=0; i<ORIGINS.length; i++){
			objFlight = new Flight();
			
			objFlight.setOrigin(FlightSearchUtils.getAirportByIATA(ORIGINS[i]));
			objFlight.setDestination(FlightSearchUtils.getAirportByIATA(DESTINATIONS[i]));
			objFlight.setAirline(FlightSearchUtils.getAirlineFromFlightNumber(FLIGHT_NUMBERS[i]));
			objFlight.setFlightNumber(FLIGHT_NUMBERS[i]);
			objFlight.setBasePrice(new Price(BigDecimal.valueOf(PRICES[i])));
			
			flightsList.add(objFlight);
		}
		
		FlightsData.setFlightsList(flightsList);
	}
	
	/**
	 * Delete the lists
	 */
	public static void deleteMocks(){
		FlightsData.setAirportsList(null);
		FlightsData.setAirlinesList(null);
		FlightsData.setFlightsList(null);
	}
}
