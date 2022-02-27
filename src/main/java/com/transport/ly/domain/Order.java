package com.transport.ly.domain;

public class Order {
	private String orderNumber;
	private int priority;
	private DestinationAirport destinationAirport;

	public Order(){

	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public DestinationAirport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(DestinationAirport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	@Override
	public String toString() {
		return String.format("Order number = %s, order priority = %d, order destination = %s", orderNumber, priority, destinationAirport);
	}
}
