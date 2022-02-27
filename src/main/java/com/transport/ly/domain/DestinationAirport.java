package com.transport.ly.domain;

public enum DestinationAirport {

	YYZ("Toronto"),
	YYC("Calgary"),
	YVR("Vancouver"),
	YYE("Fort Nelson");


	private final String value;

	DestinationAirport(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
