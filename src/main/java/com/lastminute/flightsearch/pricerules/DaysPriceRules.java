package com.lastminute.flightsearch.pricerules;

/**
 * This enumeration stores the different price rules 
 * depending on the days prior to departure date.
 * @author raulgrande83
 *
 */
public enum DaysPriceRules {

	//more than 30 (i.e. >= 31) | 80% of the base price
	RULE_1(31, Long.MAX_VALUE, 0.8),
	
	//30 - 16 	| 100% of the base price
	RULE_2(16, 30, 1),
	
	//15 - 3 	| 120% of the base price
	RULE_3(3, 15, 1.2),
	
	//less that 3 (i.e. <= 2) | 150% of the base price
	RULE_4(1, 2, 1.5);
	
	//The days when starts the price rule
	private long daysMin;
	//The days when ends the price rule
	private long daysMax;
	//The percentage to by applied over the base price
	private double percentage;
	
	/**
	 * Constructor for the price rule 
	 * @param min Days when stars the price rule
	 * @param max Days when ends the price rule
	 * @param percent The percentage to by applied over the base price
	 */
	private DaysPriceRules(long min, long max, double percent){
		daysMin = min;
		daysMax = max;
		percentage = percent;
	}
	
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public long getDaysMin() {
		return daysMin;
	}
	public void setDaysMin(long daysMin) {
		this.daysMin = daysMin;
	}
	public long getDaysMax() {
		return daysMax;
	}
	public void setDaysMax(long daysMax) {
		this.daysMax = daysMax;
	}
}
