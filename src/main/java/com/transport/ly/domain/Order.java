package com.transport.ly.domain;

public class Order {
	private String orderNumber;
	private int priority;
	private DestinationAirport destinationAirport;

	public Order(){}

	public String getOrderNumber() {
		return orderNumber;
	}

	public int getPriority() {
		return priority;
	}

	public DestinationAirport getDestinationAirport() {
		return destinationAirport;
	}

	@Override
	public String toString() {
		return String.format("Order number = %s, order priority = %d, order destination = %s", orderNumber, priority, destinationAirport);
	}
}
