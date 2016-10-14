package com.lastminute.flightsearch.beans;

/**
 * @author raulgrande83
 * This class represents the airport entity
 */
public class Airport {

	//Stores the IATA code
	private String IATACode;
	//Stores the name of the city
	private String city;
	
	/**
	 * Airport constructor with no parameters
	 */
	public Airport() {}
	/**
	 * Airport constructor that accepts the IATA Code and the name of the city
	 */
	public Airport(String IATACodeParam, String cityParam) {
		this.IATACode = IATACodeParam;
		this.city = cityParam;
	}
	
	
	//GETTERS AND SETTERS
	
	public String getIATACode() {
		return IATACode;
	}
	
	public void setIATACode(String iATACode) {
		IATACode = iATACode;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
		
}
