package com.lastminute.flightsearch.beans;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SearchTest {

	private Search search = null;
	private Search searchNull = new Search();
	private static final int ADULTS = 2;
	private static final int CHILDREN = 1;
	private static final int INFANTS = 1;
	private static String ORIGIN = "MAD";
	private static String DESTINATION = "BCN";
	private static String DATE = "05/08/2017";
	
	@Before
	public void createSearch(){
		search = new Search();
		search.setAdults(ADULTS);
		search.setChildren(CHILDREN);
		search.setInfants(INFANTS);
		search.setOrigin(ORIGIN);
		search.setDestination(DESTINATION);
		search.setDateString(DATE);
	}
	
	@Test
	public void testFields() {
		assertEquals("The number of adults should be "+ADULTS, ADULTS, search.getAdults());
		assertEquals("The number of children should be "+CHILDREN, CHILDREN, search.getChildren());
		assertEquals("The number of infants should be "+INFANTS, INFANTS, search.getInfants());
		assertEquals("The origin should be "+ORIGIN, ORIGIN, search.getOrigin());
		assertEquals("The destination should be "+DESTINATION, DESTINATION, search.getDestination());
		assertEquals("The date should be "+DATE, DATE, search.getDateString());
	}

	@Test
	public void testFieldsNull() {
		assertEquals("The number of adults should be 0", 0, searchNull.getAdults());
		assertEquals("The number of children should be 0", 0, searchNull.getChildren());
		assertEquals("The number of infants should be 0", 0, searchNull.getInfants());
		assertNull("The origin should be null", searchNull.getOrigin());
		assertNull("The destination should be null", searchNull.getDestination());
		assertNull("The date should be null", searchNull.getDateString());
	}
}
