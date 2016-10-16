package com.lastminute.flightsearch.beans;

import java.math.BigDecimal;

/**
 * This class represents an airline.
 * @author raulgrande83
 *
 */
public class Airline {

	//Stores the IATA code
	private String IATACode;
	//Stores the name of the airline
	private String name;
	//Stores the price of infant passengers
	private Price infantPrice;
	
	/**
	 * Constructor with IATACode, name, infantPrice
	 * @param IATACodeParam The IATA Code of the airline
	 * @param nameParam The name of the airline
	 * @param infantPriceParam The price for infant passengers
	 */
	public Airline(String IATACodeParam, String nameParam, BigDecimal infantPriceParam) {
		this.IATACode = IATACodeParam;
		this.name = nameParam;
		this.infantPrice = new Price(infantPriceParam);
	}
	
	public Airline(){}

	
	//GETTERS AND SETTERS
	
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

	public Price getInfantPrice() {
		return infantPrice;
	}

	public void setInfantPrice(Price infantPrice) {
		this.infantPrice = infantPrice;
	}	

}
