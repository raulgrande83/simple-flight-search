package com.lastminute.flightsearch.beans;

/**
 * This class represents a flight.
 * @author raulgrande83
 *
 */
public class Flight {

	//The airline that operates this flight
	private Airline airline;
	//The flight number
	private String flightNumber;
	//The airport of origin
	private Airport origin;
	//The airport of destination
	private Airport destination;
	//The number of adult passengers
	private int adultPassengers;
	//The number of infant passengers
	private int infantPassengers;
	//The number of child passengers
	private int childPassengers;
	//The number of days to departure date
	private long daysToDeparture;
	//The base price of the flight
	private Price basePrice;
	//The total price calculated
	private Price totalPrice;
	
	
	//GETTERS AND SETTERS
	
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Airport getOrigin() {
		return origin;
	}
	public void setOrigin(Airport origin) {
		this.origin = origin;
	}
	public Airport getDestination() {
		return destination;
	}
	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	public int getAdultPassengers() {
		return adultPassengers;
	}
	public void setAdultPassengers(int adultPassengers) {
		this.adultPassengers = adultPassengers;
	}
	public int getInfantPassengers() {
		return infantPassengers;
	}
	public void setInfantPassengers(int infantPassengers) {
		this.infantPassengers = infantPassengers;
	}
	public int getChildPassengers() {
		return childPassengers;
	}
	public void setChildPassengers(int childPassengers) {
		this.childPassengers = childPassengers;
	}
	public long getDaysToDeparture() {
		return daysToDeparture;
	}
	public void setDaysToDeparture(long daysToDeparture) {
		this.daysToDeparture = daysToDeparture;
	}
	public Price getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(Price basePrice) {
		this.basePrice = basePrice;
	}
	public Price getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Price totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
