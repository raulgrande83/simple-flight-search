package com.lastminute.flightsearch.beans;

import java.math.BigDecimal;

public class Airline {

	//Stores the IATA code
	private String IATACode;
	//Stores the name of the airline
	private String name;
	//Stores the price of 
	private Money infantPrice;
	
	public Airline(String IATACodeParam, String nameParam, BigDecimal infantPriceParam) {
		this.IATACode = IATACodeParam;
		this.name = nameParam;
		this.infantPrice = new Money(infantPriceParam);
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

	public Money getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(Money infantPrice) {
		this.infantPrice = infantPrice;
	}

}
