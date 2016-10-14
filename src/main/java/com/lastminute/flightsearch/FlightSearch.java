package com.lastminute.flightsearch;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.beans.Money;
import com.lastminute.flightsearch.beans.Search;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.exceptions.SearchParamsException;
import com.lastminute.flightsearch.pricerules.DaysPriceRules;
import com.lastminute.flightsearch.pricerules.PassengerPriceRules;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

public class FlightSearch {

	private static Search search;
	private static List<Flight> resultFlights = null;
	
	public static List<Flight> doFlightSearch(String originParam, String destinationParam, String dateParam, int adultsParam, int infantsParam, int childrenParam){
		
		try {
			//Get the params and validate them
			getAllParams(originParam, destinationParam, dateParam, adultsParam, infantsParam, childrenParam);
			
			//Get the flights that have the origin and destination
			resultFlights = FlightSearchUtils.getFlightsOriginDestination(search.getOrigin(), search.getDestination());
			
			//Calculate and set the prices for each flight
			calculateFlightPrices();
			
			//Print results
			printSearchAndResults();
			
		} catch (SearchParamsException e) {
			System.err.println("There has been errors parsing the search parameters.");
			e.printStackTrace();
		}
		
		//Return the flights with all the calculated data
		return resultFlights;
	}
	
	
	private static void printSearchAndResults(){
		System.out.println(" - Search - ");
		System.out.println("\t Passengers: "+search.getAdults()+" adults, "+search.getChildren()+" children, "+search.getInfants()+" infants.");
		System.out.println("\t Dates: "+search.getDateString()+", "+FlightSearchUtils.calculateDepartureDays(search.getFlightDate())+" days prior to departure date");
		System.out.println("\t Flying: "+search.getOrigin()+" -> "+search.getDestination());
		System.out.println();
		
		System.out.println(" -- Results -- ");
		
		if(resultFlights.isEmpty()){
			System.out.println("\t* No flights available with those parameters.");
		}else{
			for(Flight printFlight:resultFlights){
				System.out.println("\t* "+printFlight.getFlightNumber()+", "+printFlight.getTotalPrice());
			}
		}
	}
	
	
	private static void getAllParams(String originParam, String destinationParam, String dateParam, int adultsParam, int infantsParam, int childrenParam) throws SearchParamsException{
		
		//Set the params into the FlightSearch
		search = new Search();
		search.setOrigin(originParam);
		search.setDestination(destinationParam);
		search.setAdults(adultsParam);
		search.setInfants(infantsParam);
		search.setChildren(childrenParam);
		search.setDateString(dateParam);
		
		//Validate all params
		validateSearchParameters();
	}
	
	
	private static void validateSearchParameters() throws SearchParamsException{
		
		List<String> paramsErrors = new ArrayList<String>();
		
		//Validate that number of adults passengers is >= 0
		if(search.getAdults() < 0){
			paramsErrors.add("The number of adult passengers must be 0 or more.");
		}
		//Validate that number of infant passengers is >= 0
		if(search.getInfants() < 0){
			paramsErrors.add("The number of infant passengers must be 0 or more.");
		}
		//Validate that number of child passengers is >= 0
		if(search.getChildren() < 0){
			paramsErrors.add("The number of child passengers must be 0 or more.");
		}
		//Validate if all number of passengers equals 0
		if(search.getAdults() == 0 && search.getInfants() == 0 && search.getChildren() == 0){
			paramsErrors.add("The total number of passengers must be more than 0.");
		}
		try {
			//Parse the date received
			search.setFlightDate(FlightSearchUtils.getFlightDate(search.getDateString()));
			
			//Validate that the departure date should be greater than today
			Date today = new Date();
			if(today.after(search.getFlightDate())){
				paramsErrors.add("The departure date must be greater than today.");
			}
			
		} catch (ParseException e) {
			paramsErrors.add("The format of the date is not valid. Use "+Constants.DATE_FORMAT);
		}
		
		
		
		
		//If there are any error throw the SearchParamsException
		if(!paramsErrors.isEmpty()){
			StringBuffer strErrors = new StringBuffer();
			strErrors.append("There are ");
			strErrors.append(paramsErrors.size());
			strErrors.append(" errors in the search parameters:\n");
			
			for(String err : paramsErrors){
				strErrors.append("\t- ");
				strErrors.append(err);
				strErrors.append("\n");
			}
			
			throw new SearchParamsException(strErrors.toString());
		}
			
	}
	
	private static void calculateFlightPrices(){
		
		if(!resultFlights.isEmpty()){
			//Depending on the flight date it applies a price rule
			final DaysPriceRules priceRule = FlightSearchUtils.getPriceRule(search.getFlightDate());
			
			for(Flight flight:resultFlights){
				BigDecimal totalPrice = new BigDecimal(0.0);
				
				//Calculate Adults price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getAdults(), PassengerPriceRules.ADULT, priceRule));
				//Calculate Children price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getChildren(), PassengerPriceRules.CHILD, priceRule));
				//Calculate Infants price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getInfants(), PassengerPriceRules.INFANT, priceRule));
				
				//Set the total price on the flight
				flight.setTotalPrice(new Money(totalPrice));
			}
		}
	}
	
	private static BigDecimal calculatePassengerPrice(Flight flight, int passengersNumber, PassengerPriceRules passengerRule, DaysPriceRules priceRule){
		
		BigDecimal passengersPrice = new BigDecimal(0.0);
		
		//Only make calcs if there are any passengers
		if(passengersNumber > 0){
		
			//Check if there is a fixed price for that passenger price rule
			if(!passengerRule.isFixedPrice()){
				passengersPrice = new BigDecimal(priceRule.getPercentage()).multiply(flight.getBasePrice().getAmount());
				
			}else if(passengerRule.equals(PassengerPriceRules.INFANT)){
				//Fixed price only for infant passengers
				passengersPrice = flight.getAirline().getInfantPrice().getAmount();
			}
			
			//If there's a disccount, it applies over the price
			if(passengerRule.getDiscount() > 0){
				passengersPrice = new BigDecimal(passengerRule.getDiscount()).multiply(passengersPrice);
			}
		}	
		//Return the passenger's price for all passengers of this type
		passengersPrice = passengersPrice.multiply(new BigDecimal(passengersNumber));
		return passengersPrice;
	}
}
