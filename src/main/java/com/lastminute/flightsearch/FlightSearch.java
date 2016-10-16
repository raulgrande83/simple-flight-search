package com.lastminute.flightsearch;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.beans.Price;
import com.lastminute.flightsearch.beans.Search;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.exceptions.SearchParamsException;
import com.lastminute.flightsearch.pricerules.DaysPriceRules;
import com.lastminute.flightsearch.pricerules.PassengerPriceRules;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

/**
 * This class implements the logic to receive search parameters, validate them and 
 * do the logic to search and calculate prices for each resulting flight.
 * @author raulgrande83
 *
 */
public class FlightSearch {

	//Contains the search parameters
	private static Search search;
	//Contains the resulting flights
	private static List<Flight> resultFlights = null;
	
	/**
	 * Method that receives the parameters and search for flights
	 * 
	 * @param originParam The IATA code of the airport of origin
	 * @param destinationParam The IATA code of the airport of destination
	 * @param dateParam The date when the user wants to fly
	 * @param adultsParam How many adult passengers
	 * @param infantsParam How many infant passengers
	 * @param childrenParam How many child passengers
	 * @return List<Flight> The list with all the information for the resulting flights
	 * @throws SearchParamsException Is thrown when one or more parameters are not valid
	 */
	public static List<Flight> doFlightSearch(String originParam, String destinationParam, String dateParam, int adultsParam, int infantsParam, int childrenParam) throws SearchParamsException{
		
		//Get the params and validate them
		getAllParams(originParam, destinationParam, dateParam, adultsParam, infantsParam, childrenParam);
		
		//Get the flights that have the origin and destination
		resultFlights = FlightSearchUtils.getFlightsOriginDestination(search.getOrigin(), search.getDestination());
		
		//Calculate and set the prices for each flight
		calculateFlightPrices();
		
		//Return the flights with all the calculated data
		return resultFlights;
	}
	
	/**
	 * Method that set the parameters into the Search object
	 * 
	 * @param originParam The IATA code of the airport of origin
	 * @param destinationParam The IATA code of the airport of destination
	 * @param dateParam The date when the user wants to fly
	 * @param adultsParam How many adult passengers
	 * @param infantsParam How many infant passengers
	 * @param childrenParam How many child passengers
	 * @throws SearchParamsException Is thrown when one or more parameters are not valid
	 */
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
	
	/**
	 * Validates all search parameters
	 * @throws SearchParamsException Is thrown when one or more parameters are not valid
	 */
	private static void validateSearchParameters() throws SearchParamsException{
		//Stores the errors during validations
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
			Calendar today = Calendar.getInstance();
			if(today.after(search.getFlightDate())){
				paramsErrors.add("The departure date must be greater than today.");
			}
			
		} catch (ParseException e) {
			paramsErrors.add("The format of the date is not valid. Use "+Constants.DATE_FORMAT);
		}
		//Validate the origin is not null or empty
		if(search.getOrigin()==null || search.getOrigin().isEmpty()){
			paramsErrors.add("The origin must be informed.");
		}
		//Validate the destination is not null or empty
		if(search.getDestination()==null || search.getDestination().isEmpty()){
			paramsErrors.add("The destination must be informed.");
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
	
	/**
	 * This method calculates the flight price, depending on the flight date and number of passengers
	 */
	private static void calculateFlightPrices(){
		
		if(!resultFlights.isEmpty()){
			//Depending on the flight date it applies a price rule
			final DaysPriceRules priceRule = FlightSearchUtils.getPriceRule(search.getFlightDate());
			
			for(Flight flight:resultFlights){
				
				//Set days prior to departure date on flight
				flight.setDaysToDeparture(FlightSearchUtils.calculateDepartureDays(search.getFlightDate()));
				
				BigDecimal totalPrice = new BigDecimal(0.0);
				
				//Calculate Adults price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getAdults(), PassengerPriceRules.ADULT, priceRule));
				//Calculate Children price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getChildren(), PassengerPriceRules.CHILD, priceRule));
				//Calculate Infants price
				totalPrice = totalPrice.add(calculatePassengerPrice(flight, search.getInfants(), PassengerPriceRules.INFANT, priceRule));
				
				//Set the total price on the flight
				flight.setTotalPrice(new Price(totalPrice));
			}
		}
	}
	
	/**
	 * This method calculate the price depending on the type and number of passengers.
	 * @param flight The flight is being calculated
	 * @param passengersNumber Number of passengers of this type
	 * @param passengerRule The rule that applies to that type
	 * @param priceRule The rule that applies to the date of the flight
	 * @return BigDecimal The price calculated with all the parameters
	 */
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
