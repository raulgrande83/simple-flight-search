package com.lastminute.flightsearch.beans;

import java.util.Date;

/**
 * This class is used to store all the search parameters
 * @author raulgrande83
 *
 */
public class Search {

	//Origin parameter
	private String origin;
	//Destination parameter
	private String destination;
	//Date calculated using dateString
	private Date flightDate;
	//Date parameter
	private String dateString;
	//Number of adults
	private int adults;
	//Number of infants
	private int infants;
	//Number of children
	private int children;
	
	
	//GETTERS AND SETTERS
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}
	public int getAdults() {
		return adults;
	}
	public void setAdults(int adults) {
		this.adults = adults;
	}
	public int getInfants() {
		return infants;
	}
	public void setInfants(int infants) {
		this.infants = infants;
	}
	public int getChildren() {
		return children;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
}
