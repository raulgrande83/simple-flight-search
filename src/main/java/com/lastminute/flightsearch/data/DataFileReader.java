package com.lastminute.flightsearch.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lastminute.flightsearch.beans.Airline;
import com.lastminute.flightsearch.beans.Airport;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.beans.Money;
import com.lastminute.flightsearch.constants.Constants;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

public class DataFileReader {
	
	
	public static List<Airport> getAirportData() throws FileNotFoundException{
		
		//The airports list to be returned
		List<Airport> airportsList = new ArrayList<Airport>();
		
		//Read the file where the airports data is stored 
		Scanner scanner = new Scanner(new File(Constants.DATA_AIRPORTS));
		Scanner dataScanner = null;
		int index = 0;
		
		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(Constants.DATA_DELIMITER);
			
			Airport airport = new Airport();

			while (dataScanner.hasNext()) {
				String data = dataScanner.next();
				
				//First column is IATA Code
				if (index == 0 && data != null)
					airport.setIATACode(data.trim());
				//Second column is City Name
				else if (index == 1 && data!=null)
					airport.setCity(data.trim());
				
				index++;
			}
			//Reset index
			index = 0;
			//Add the airport object to the list
			airportsList.add(airport);
		}
		//End using scanner
		scanner.close();
		
        return airportsList;
	}
	
	public static List<Airline> getAirlinesData() throws FileNotFoundException{
		
		//Read the file where the airports data is stored 
		Scanner scanner = new Scanner(new File(Constants.DATA_AIRLINES));
		Scanner dataScanner = null;
		int index = 0;
		
		//The airlines list to be returned
		List<Airline> airlinesList = new ArrayList<Airline>();
		
		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(Constants.DATA_DELIMITER);
			
			Airline airline = new Airline();

			while (dataScanner.hasNext()) {
				String data = dataScanner.next();
				
				//First column is IATA Code
				if (index == 0 && data != null)
					airline.setIATACode(data.trim());
				//Second column is Airline Name
				else if (index == 1 && data!=null)
					airline.setName(data.trim());
				//Third column is Infant Price
				else if (index == 2 && data!=null)
					airline.setInfantPrice(new Money(BigDecimal.valueOf(Double.valueOf(data.trim()))));
				
				index++;
			}
			//Reset index
			index = 0;
			//Add the airport object to the list
			airlinesList.add(airline);
		}
		//End using scanner
		scanner.close();
		
        return airlinesList;
	}
	
	
	public static List<Flight> getFlightsData() throws FileNotFoundException{
		
		//Read the file where the flights data is stored 
		Scanner scanner = new Scanner(new File(Constants.DATA_FLIGHTS));
		Scanner dataScanner = null;
		int index = 0;
		
		//The flights list to be returned
		List<Flight> flightsList = new ArrayList<Flight>();
		
		while (scanner.hasNextLine()) {
			dataScanner = new Scanner(scanner.nextLine());
			dataScanner.useDelimiter(Constants.DATA_DELIMITER);
			
			Flight flight = new Flight();

			while (dataScanner.hasNext()) {
				String data = dataScanner.next();
				
				//First column is airport of origin
				if (index == 0 && data != null)
					flight.setOrigin(FlightSearchUtils.getAirportByIATA(data.trim()));
				
				//Second column is airport of destination
				else if (index == 1 && data!=null)
					flight.setDestination(FlightSearchUtils.getAirportByIATA(data.trim()));
				
				//Third column is Flight Number (2 digits for Airline + 4 more digits)
				else if (index == 2 && data!=null){
					//Set the airline from the flight number
					flight.setAirline(FlightSearchUtils.getAirlineFromFlightNumber(data.trim()));
					//Set the flight number
					flight.setFlightNumber(data.trim());
				}
				
				//Fourth column is base price
				else if (index == 3 && data!=null)
					flight.setBasePrice(new Money(BigDecimal.valueOf(Double.valueOf(data.trim()))));
				
				index++;
			}
			//Reset index
			index = 0;
			//Add the airport object to the list
			flightsList.add(flight);
		}
		//End using scanner
		scanner.close();
		
        return flightsList;
	}

}
