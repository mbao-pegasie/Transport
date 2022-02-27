import com.transport.ly.domain.DepartureAirport;
import com.transport.ly.domain.DestinationAirport;
import com.transport.ly.domain.Flight;
import com.transport.ly.domain.Order;
import com.transport.ly.managerment.FlightManager;
import com.transport.ly.managerment.InventoryManager;
import com.transport.ly.util.Helper;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TransportApplication {

	public static void main(String[] args) throws IOException, ParseException {

		String fileName = "src/main/resources/Coding-assigment-orders.json";

		List<Flight> flights = Arrays.asList(
				new Flight("Flight1", DepartureAirport.YUL, DestinationAirport.YYZ, "1"),
				new Flight("Flight2", DepartureAirport.YUL, DestinationAirport.YYC, "1"),
				new Flight("Flight3", DepartureAirport.YUL, DestinationAirport.YVR, "1"),
				new Flight("Flight4", DepartureAirport.YUL, DestinationAirport.YYZ, "2"),
				new Flight("Flight5", DepartureAirport.YUL, DestinationAirport.YYC, "2"),
				new Flight("Flight6", DepartureAirport.YUL, DestinationAirport.YVR, "2")
		);

		// For user story #1
		FlightManager user = new FlightManager(flights);
		user.displayAllFlight();

		//For user story #2

		//Flights from one specific Airport to load Orders: for example: Montreal
		List<Flight> flightFromMontreal = user.getFlightListByDeparture(DepartureAirport.YUL);
		user.setFightList(flightFromMontreal);

		//load Orders from file
		Helper.rawDataConverter();
		InventoryManager manager = new InventoryManager();
		manager.loadedInventory(fileName);
		List<Order> orderList = manager.getCurrentInventory();
		System.out.println("Total Orders in list = " + orderList.size());

		//List all Destinations in the Orders:
		List<DestinationAirport> orderToGo = manager.getAllDestinationAirport(orderList);
		for (DestinationAirport des : orderToGo) {
			//List all Orders to this destination:(by priority)
			List<Order> orderByDes = manager.sortOrderListByDestinationAndPriority(des);
			//List of all Flights to this destination:(By day)
			List<Flight> flightByDes = user.sortFlightListByDestinationAndDay(des);

			int totalFlights = flightByDes.size();
			int totalOriginalOrders = orderByDes.size();
			System.out.println("Total " + totalFlights + " flights" + " to " + des);
			System.out.println("Total " + totalOriginalOrders + " orders" + " to " + des );

			if(totalFlights > 0) {
				for (int i = 0; i < totalFlights; i++) {
					Flight f = flightByDes.get(i);
					if(orderByDes.size() > 0 ) {
						//check order one by one
						for (Iterator<Order> iterator = orderByDes.iterator(); iterator.hasNext();) {
							Order o = iterator.next();
							if(user.loadOrder(f, o)){
								System.out.println(String.format("Order : %s, flightNumber : %s, departure: %s, arrival : %s, day : %s",
										o.getOrderNumber(), f.getFlightNumber(), f.getDepartureAirport(), f.getDestinationAirport(), f.getFlightDay()));
								//order loaded to flight successfully, remove it from list
								iterator.remove();
							} else {
								//flight already full, move to next flight
								System.out.println("Flight " + f.getFlightNumber() + " is full");
								break;
							}
						}
					} else{
						System.out.println("All orders to " + des + " loaded!");
						break;
					}
					//for those left orders that all flights to destination already full
					if(i == totalFlights && orderByDes.size() > 0){
						for (Order notLoaded : orderByDes) {
							System.out.println(String.format("Order : %s, flightNumber : not scheduled", notLoaded.getOrderNumber()));
						}
					}
				}
			} else {
				//for those orders no flights at all:
				for (Order notLoaded : orderByDes) {
					System.out.println(String.format("Order : %s, flightNumber : not scheduled", notLoaded.getOrderNumber()));
				}
			}
		}
	}
}
