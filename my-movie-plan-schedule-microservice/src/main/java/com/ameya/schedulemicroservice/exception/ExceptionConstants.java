package com.ameya.schedulemicroservice.exception;

public enum ExceptionConstants {
	
	USER_NOT_FOUND("user.not.found"),
	GENERAL_EXCEPTION("general.exception"),
	NOMINATION_SUCCESS("nomination.success"),
	USER_ALREADY_EXISTS("user.already.exists"),
	NOMINATION_WINDOW_CLOSED("nomination.window.closed"),
	USER_UPDATE_SUCCESS("user.update.success"),
	ROLE_ALREADY_ASSINGED("user.role.assignment"),
	MATCH_UPDATE_SUCCESS("match.update.success"),
	NOMINATION_NOT_FOUND("no.such.nomination"),
	NOMINATION_ALREADY_EXISTS("nomination.already.exists"),
	MATCH_DELETE_SUCCESS("match.delete.success"),
	NOMINATION_UPDATE_SUCCESS("nomination.update.success"),
	USER_CREATION_FAILED("user.creation.failed"),
	USER_DELETE_SUCCESS("user.delete.success"),
	CITY_ALREADY_EXISTS("city.already.exists"),
	CITY_NOT_FOUND("city.not.found"),
	GENRE_ALREADY_EXISTS("genre.already.exists"),
	GENRE_NOT_FOUND("genre.not.found"),
	LANGUAGE_ALREADY_EXISTS("language.already.exists"),
	LANGUAGE_NOT_FOUND("language.not.found"),
	SHOWTIME_ALREADY_EXISTS("showtime.already.exists"),
	SHOWTIME_NOT_FOUND("showtime.not.found"),
	THEATER_ALREADY_EXISTS("theater.already.exists"),
	THEATER_NOT_FOUND("theater.not.found"),
	TIER_ALREADY_EXISTS("tier.already.exists"),
	TIER_NOT_FOUND("tier.not.found"),
	MOVIE_ALREADY_EXISTS("movie.already.exists"),
	MOVIE_NOT_FOUND("movie.not.found"),
	SCHEDULE_ALREADY_EXISTS("schedule.already.exists"),
	SCHEDULE_NOT_FOUND("schedule.not.found"),
	SCHEDULE_NOT_POSSIBLE("schedule.not.possible"),
	BOOKING_ALREADY_EXISTS("booking.already.exists"),
	BOOKING_NOT_FOUND("booking.not.found"),
	BOOKING_NOT_POSSIBLE("booking.not.possible"),
	SEAT_ALREADY_EXISTS("seat.already.exists"),
	SEAT_NOT_FOUND("seat.not.found"),
	MOVIE_NOT_ACTIVE("movie.not.active"),
	PARTNER_ALREADY_EXISTS("partner.already.exists"), 
	NO_SUCH_PARTNER("no.such.partner");
	
	private final String type;

	private ExceptionConstants(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
