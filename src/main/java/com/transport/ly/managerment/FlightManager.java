package com.transport.ly.managerment;

import com.transport.ly.domain.DepartureAirport;
import com.transport.ly.domain.DestinationAirport;
import com.transport.ly.domain.Flight;
import com.transport.ly.domain.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class FlightManager {

	private List<Flight> fightList;

	public FlightManager(List<Flight> list) {
		fightList = new ArrayList<Flight>(list);
	}

	public List<Flight> getFlightListByDeparture(DepartureAirport value) {
		return fightList.stream().filter(s -> (s.getDepartureAirport().name()).equals(value.name())).collect(Collectors.toList());
	}

	private List<Flight> getFlightListByDestination(DestinationAirport value) {
		return fightList.stream().filter(s -> (s.getDestinationAirport().name()).equals(value.name())).collect(Collectors.toList());
	}

	public List<Flight> sortFlightListByDestinationAndDay(DestinationAirport value) {
		List<Flight> listByDestination = getFlightListByDestination(value);
		if(listByDestination.size() > 0) {
			listByDestination.sort(Comparator.comparing(Flight::getFlightDay));
		}
		return listByDestination;
	}

	public void displayAllFlight() {
		if(fightList.size() > 0) {
			for (Flight f : fightList) {
				System.out.print(f + "\n");
			}
		} else {
			System.out.println("There is no flights to display!");
		}

	}

	public void displayAllFlight(List<Flight> flightListGiven) {
		if(flightListGiven.size() > 0) {
			for (Flight f : flightListGiven) {
				System.out.print(f + "\n");
			}
		} else {
			System.out.println("There is no flights to display!");
		}
	}

	public boolean loadOrder(Flight flight, Order order) {
		boolean loaded = false;
		if(flight.getDestinationAirport().name().equals(order.getDestinationAirport().name())) {
			if (!flight.isCapacityFull()) {
				flight.setCapacity(flight.getCapacity() + 1);
				loaded = true;
				if (flight.getCapacity() == flight.getMAX_CAPACITY()) {
					flight.setCapacityFull(true);
				}
			} else {
				System.out.println("The flight " + flight.getFlightNumber() + " is already full, the order: " + order.getOrderNumber() + " not loaded");
			}
		} else {
			System.out.println("The flight destination is: " + flight.getDestinationAirport().name() +
					", but order destination is: " + order.getDestinationAirport().name() + "! Lord to wring flight!");
		}

		return loaded;
	}

	public void setFightList(List<Flight> fightList) {
		this.fightList = fightList;
	}
}
