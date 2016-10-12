import java.util.List;

import com.lastminute.flightsearch.beans.Flight;
import com.lastminute.flightsearch.data.FlightsData;
import com.lastminute.flightsearch.utils.FlightSearchUtils;

public class Execute {

	public static void main(String[] args) {
		
		List<Flight> flights = FlightSearchUtils.getFlightsOriginDestination("BCN", "MAD");
		
		FlightSearchUtils.getAirlineByIATA("IZB");
		FlightSearchUtils.getAirlineByIATA("IB");
		FlightSearchUtils.getAirlineByIATA("U2");
		
		

	}

}
