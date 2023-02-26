package com.ameya.usermicroservice.utils;

public enum CrudMessage {
	
	CITY_DELETE_SUCCESS("city.delete.success"),
	THEATER_DELETE_SUCCESS("theater.delete.success"),
	SCHEDULE_DELETE_SUCCESS("schedule.delete.success"),
	MOVIE_DELETE_SUCCESS("movie.delete.success"),
	LANGUAGE_DELETE_SUCCESS("language.delete.success"),
	GENRE_DELETE_SUCCESS("genre.delete.success");
	
	private final String type;

	private CrudMessage(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}

}
