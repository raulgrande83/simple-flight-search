package com.lastminute.flightsearch.pricerules;

/**
 * This enumeration stores the different price rules 
 * depending on the type of passenger (Adult / Child / Infant).
 * @author raulgrande83
 *
 */
public enum PassengerPriceRules {
	
	//full price (i.e. price resulting from the *days to departure date* rule)
	ADULT(0, false),
	//33% discount of the price calculated according to the *days to departure date* rule
	CHILD(0.67, false),
	//fixed price depending on the airline. Rule *days to departure date* is not applied for infants
	INFANT(0, true);
	
	//The discount to be applied
	private double discount;
	//Checks if there is a fixed price for this type of passenger
	private boolean fixedPrice;
	
	/**
	 * Constructor for the passenger price rule
	 * @param disc The discount to be applied
	 * @param fixed Boolean that sets if there is a fixed price for this type of passenger
	 */
	private PassengerPriceRules(double disc, boolean fixed){
		discount = disc;
		fixedPrice = fixed;
	}
	
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public boolean isFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(boolean fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	
	

}
