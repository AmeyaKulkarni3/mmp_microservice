package com.ameya.theaterservice.utils;

public enum CrudMessage {
	
	THEATER_DELETE_SUCCESS("theater.delete.success"),
	CITY_DELETE_SUCCESS("theater.delete.success");

	private final String type;

	private CrudMessage(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
