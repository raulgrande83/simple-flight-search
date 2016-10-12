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
	
	/**
	 * Returns the IATA Code
	 * @return
	 */
	public String getIATACode() {
		return IATACode;
	}
	//Sets the IATACode
	public void setIATACode(String iATACode) {
		IATACode = iATACode;
	}
	//Returns the name of the city
	public String getCity() {
		return city;
	}
	//Sets the name of the city
	public void setCity(String city) {
		this.city = city;
	}
		
}
