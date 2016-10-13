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

public class FlightSearchUtils {
	
	/**
	 * Returns an airline given its IATA Code
	 * @return
	 */
	public static Airline getAirlineByIATA(String IATACode){
		
		Optional<Airline> result = FlightsData.getAirlinesList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .findFirst();
		
		return result.get();
	}
	
	/**
	 * Returns an airport given its IATA Code
	 * @return
	 */
	public static Airport getAirportByIATA(String IATACode){
		
		Optional<Airport> result = FlightsData.getAirportsList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .findFirst();
		
		return result.get();
	}
	
	
	public static List<Flight> getFlightsOriginDestination(String origin, String destination){
		
		List<Flight> result = FlightsData.getFlightsList().stream()
			    .filter(a -> Objects.equals(a.getOrigin().getIATACode(), origin))
			    .filter(a -> Objects.equals(a.getDestination().getIATACode(), destination))
			    .collect(Collectors.toList());

		return result;
	}
	
	
	public static Airline getAirlineFromFlightNumber(String flightNumber){
		if(flightNumber!=null && flightNumber.length() > Constants.IATA_DIGITS){
			final String IATACode = flightNumber.substring(0, Constants.IATA_DIGITS);
			
			return getAirlineByIATA(IATACode);
		}
		return null;
	}
	
	
	public static Date getFlightDate(String dateSearch) throws ParseException{
		
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        return formatter.parse(dateSearch);
            
	}
	
	public static long calculateDepartureDays(Date departureDate){
		LocalDate today = LocalDate.now();
		LocalDate departure = new java.sql.Date(departureDate.getTime()).toLocalDate();
		
		return ChronoUnit.DAYS.between(today, departure);
		
	}
	
	public static DaysPriceRules getPriceRule(Date departureDate){
		
		final long daysPriorDeparture = calculateDepartureDays(departureDate);
		
		for(DaysPriceRules priceRule:DaysPriceRules.values()){
			if(priceRule.getDaysMin() <= daysPriorDeparture && priceRule.getDaysMax() >= daysPriorDeparture)
				return priceRule;
		}
		return null;
	}

}
