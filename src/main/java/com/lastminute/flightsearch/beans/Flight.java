package com.lastminute.flightsearch.beans;

public class Flight {
	
	private Airline airline;
	private String flightNumber;
	private Airport origin;
	private Airport destination;
	private int adultPassengers;
	private int infantPassengers;
	private int childPassengers;
	private int daysToDeparture;
	private double basePrice;
	private double totalPrice;
	
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
	public int getDaysToDeparture() {
		return daysToDeparture;
	}
	public void setDaysToDeparture(int daysToDeparture) {
		this.daysToDeparture = daysToDeparture;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
}
