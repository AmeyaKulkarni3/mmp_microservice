package com.ameya.moviemicroservice.utils;

public enum CrudMessage {

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
