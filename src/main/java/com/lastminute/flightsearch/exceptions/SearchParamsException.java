package com.lastminute.flightsearch.exceptions;

/**
 * This exception is used to validate the parameters received
 * @author raulgrande83
 *
 */
public class SearchParamsException extends Exception {

	private static final long serialVersionUID = 7485409416180557327L;
	
	public SearchParamsException(String msg){
		super(msg);
	}
	
}
