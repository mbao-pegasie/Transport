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
		listByDestination.sort(Comparator.comparing(Flight::getFlightDay));
		return listByDestination;
	}

	public List<Flight> getFlightListByDay(String value) {
		return fightList.stream().filter(s -> (s.getFlightDay()).equals(value)).collect(Collectors.toList());
	}

	public List<Flight> getFlightListByDestinationAndDay(DestinationAirport airport, String day) {
		return fightList.stream().filter(s -> ((s.getDestinationAirport().name()).equals(airport.name())) && (s.getFlightDay().equals(day))).collect(Collectors.toList());
	}

	public void displayAllFlight() {
		for (Flight f : fightList) {
			System.out.print(f + "\n");
		}
	}

	public void displayAllFlight(List<Flight> flightListGiven) {
		for (Flight f : flightListGiven) {
			System.out.print(f + "\n");
		}
	}

	public boolean loadOrder(Flight flight, Order order) {
		boolean loaded = false;
		if (!flight.isCapacityFull()) {
			flight.setCapacity(flight.getCapacity() + 1);
			loaded = true;
			if (flight.getCapacity() == flight.getMAX_CAPACITY()) {
				flight.setCapacityFull(true);
			}
		} else {
			System.out.println("The flight " + flight.getFlightNumber() + " is already full, the order: " + order.getOrderNumber() + " not loaded");
		}
		return loaded;
	}

	public List<Flight> getFightList() {
		return fightList;
	}

	public void setFightList(List<Flight> fightList) {
		this.fightList = fightList;
	}
}
