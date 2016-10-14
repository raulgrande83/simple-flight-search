package com.lastminute.flightsearch.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.data.FlightsData;
import com.lastminute.flightsearch.pricerules.DaysPriceRules;

/**
 * Class that have different utilities and methods to be used over the application
 * @author raulgrande83
 *
 */
public class FlightSearchUtils {
	
	/**
	 * Returns an airline given its IATA Code.
	 * @return Airline The resulting airline
	 */
	public static Airline getAirlineByIATA(String IATACode){
		
		//Find an airline over the airlines list 
		Optional<Airline> result = FlightsData.getAirlinesList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .findFirst();
		
		//Return the resulting airline
		return result.get();
	}
	
	/**
	 * Returns an airport given its IATA Code
	 * @return Airport The resulting airport
	 */
	public static Airport getAirportByIATA(String IATACode){
		
		//Find an airport over the airports list
		Optional<Airport> result = FlightsData.getAirportsList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .findFirst();
		
		//Return the resulting airport
		return result.get();
	}
	
	/**
	 * Returns a list of flights that match with the origin and destination passed
	 * @param origin The airport (IATA Code) of origin 
	 * @param destination The airport (IATA Code) of destination
	 * @return List<Flight> The list of resulting flights
	 */
	public static List<Flight> getFlightsOriginDestination(String origin, String destination){
		
		//Search in the list with that origin and destination
		List<Flight> result = FlightsData.getFlightsList().stream()
				//Origin
			    .filter(a -> Objects.equals(a.getOrigin().getIATACode(), origin))
			    //Destination
			    .filter(a -> Objects.equals(a.getDestination().getIATACode(), destination))
			    .collect(Collectors.toList());

		//Return the list of airports
		return result;
	}
	
	/**
	 * Method that gets the airline from flight number.
	 * Example: IB1234 -> IB, Iberia
	 * @param flightNumber The number of the flight
	 * @return Airline The airline with its code and name
	 */
	public static Airline getAirlineFromFlightNumber(String flightNumber){

		if(flightNumber!=null && flightNumber.length() > Constants.IATA_DIGITS){
			//Get IATA Code from flight number
			final String IATACode = flightNumber.substring(0, Constants.IATA_DIGITS);
			//Find the airline with the IATA Code
			return getAirlineByIATA(IATACode);
		}
		return null;
	}
	
	/**
	 * Method that returns a Date from a String in format dd/MM/yyyy
	 * @param dateSearch The string with the date
	 * @return Date The Date object from the string
	 * @throws ParseException It is thrown if the format is not valid
	 */
	public static Date getFlightDate(String dateSearch) throws ParseException{
		//Format the date
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        return formatter.parse(dateSearch);
	}
	
	/**
	 * Method that calculates the days prior to departure date
	 * @param departureDate The Date of flight departure.
	 * @return long The number of days between today and departure date
	 */
	public static long calculateDepartureDays(Date departureDate){
		//Get today date
		LocalDate today = LocalDate.now();
		LocalDate departure = new java.sql.Date(departureDate.getTime()).toLocalDate();

		//Return number of days between today and departure date 
		return ChronoUnit.DAYS.between(today, departure);
		
	}
	
	/**
	 * Method that gets the price rule depending on departure date
	 * @param departureDate The date of departure
	 * @return DaysPriceRules The price rule that apply to that departure date
	 */
	public static DaysPriceRules getPriceRule(Date departureDate){
		//Get how many days are prior to departure date
		final long daysPriorDeparture = calculateDepartureDays(departureDate);
		
		//Iterate over the enumeration of DaysPriceRules
		for(DaysPriceRules priceRule:DaysPriceRules.values()){
			
			//If the days prior to departure fits the price rule, return the price rule 
			if(priceRule.getDaysMin() <= daysPriorDeparture && priceRule.getDaysMax() >= daysPriorDeparture)
				return priceRule;
		}
		return null;
	}

}
