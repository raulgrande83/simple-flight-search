import java.text.ParseException;
import java.util.List;

import com.lastminute.flightsearch.FlightSearch;
import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

public class Execute {

	public static void main(String[] args) {
		
		List<Flight> resultFlights = FlightSearch.doFlightSearch("AMS", "FRA", "14/12/2016", 1, 0, 0);
		
		try {
			FlightSearchUtils.getPriceRule(FlightSearchUtils.getFlightDate("14/10/2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
