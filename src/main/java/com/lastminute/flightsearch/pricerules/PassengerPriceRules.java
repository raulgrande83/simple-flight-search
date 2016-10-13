package com.lastminute.flightsearch.pricerules;

public enum PassengerPriceRules {
	
	//full price (i.e. price resulting from the *days to departure date* rule)
	ADULT(0, false),
	//33% discount of the price calculated according to the *days to departure date* rule
	CHILD(0.67, false),
	//fixed price depending on the airline. Rule *days to departure date* is not applied for infants
	INFANT(0, true);
	
	private PassengerPriceRules(double disc, boolean fixed){
		discount = disc;
		fixedPrice = fixed;
	}
	
	private double discount;
	private boolean fixedPrice;
	
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
