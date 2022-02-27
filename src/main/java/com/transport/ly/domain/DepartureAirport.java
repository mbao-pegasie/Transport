package com.transport.ly.domain;

public enum DepartureAirport {
	YUL("Montreal");

	private final String value;

	DepartureAirport(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}
