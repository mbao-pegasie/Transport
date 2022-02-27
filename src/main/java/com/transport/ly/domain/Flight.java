package com.transport.ly.domain;

public class Flight {
	private final int MAX_CAPACITY;
	private String flightNumber;
	private String flightDay;
	private DepartureAirport departureAirport;
	private DestinationAirport destinationAirport;
	private String departureTime;
	private String returnTime;
	private int capacity;
	private boolean capacityFull;

	public Flight(String flightNumber, DepartureAirport departureAirport, DestinationAirport destinationAirport, String flightDay) {
		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.flightDay = flightDay;
		this.MAX_CAPACITY = 20;
		this.capacity = 0;
		this.capacityFull = false;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getFlightDay() {
		return flightDay;
	}

	public DepartureAirport getDepartureAirport() {
		return departureAirport;
	}

	public DestinationAirport getDestinationAirport() {
		return destinationAirport;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setFlightDay(String flightDay) {
		this.flightDay = flightDay;
	}

	public void setDepartureAirport(DepartureAirport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public void setDestinationAirport(DestinationAirport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isCapacityFull() {
		return capacityFull;
	}

	public void setCapacityFull(boolean capacityFull) {
		this.capacityFull = capacityFull;
	}

	public int getMAX_CAPACITY() {
		return MAX_CAPACITY;
	}

	@Override
	public String toString() {
		return String.format("Flight number = %s, departure = %s, arrival = %s, flight day =  %s", flightNumber, departureAirport.name(), destinationAirport.name(), flightDay);
	}
}
