package com.lastminute.flightsearch.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.data.FlightsData;

public class FlightSearchUtils {
	
	/**
	 * Returns an airline given its IATA Code
	 * @return
	 */
	public static Airline getAirlineByIATA(String IATACode){
		
		List<Airline> result = FlightsData.getAirlinesList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .collect(Collectors.toList());
		
		if(result.size() > 0)
			return result.get(0);
		
		return null;
	}
	
	/**
	 * Returns an airport given its IATA Code
	 * @return
	 */
	public static Airport getAirportByIATA(String IATACode){
		
		List<Airport> result = FlightsData.getAirportsList().stream()
							    .filter(a -> Objects.equals(a.getIATACode(), IATACode))
							    .collect(Collectors.toList());
		
		if(result.size() > 0)
			return result.get(0);
		
		return null;
	}
	
	
	public static List<Flight> getFlightsOriginDestination(String origin, String destination){
		
		List<Flight> result = FlightsData.getFlightsList().stream()
			    .filter(a -> Objects.equals(a.getOrigin().getIATACode(), origin))
			    .filter(a -> Objects.equals(a.getDestination().getIATACode(), destination))
			    .collect(Collectors.toList());

		return result;
	}
	
	
	public static Airline getAirlineFromFlightNumber(String flightNumber){
		if(flightNumber!=null){
			final String IATACode = flightNumber.substring(0, Constants.IATA_DIGITS);
			
			return getAirlineByIATA(IATACode);
		}
		return null;
	}

}
