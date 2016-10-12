package com.lastminute.flightsearch.beans;

public class Airline {

	//Stores the IATA code
	private String IATACode;
	//Stores the name of the airline
	private String name;
	//Stores the price of 
	private double infantPrice;
	
	public Airline(String IATACodeParam, String nameParam, double infantPriceParam) {
		this.IATACode = IATACodeParam;
		this.name = nameParam;
		this.infantPrice = infantPriceParam;
	}
	
	public Airline(){}

	public String getIATACode() {
		return IATACode;
	}

	public void setIATACode(String iATACode) {
		IATACode = iATACode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(double infantPrice) {
		this.infantPrice = infantPrice;
	}

}
