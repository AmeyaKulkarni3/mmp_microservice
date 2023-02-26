package com.ameya.schedulemicroservice.service;

import java.util.List;

import com.ameya.schedulemicroservice.dto.TheaterDto;
import com.ameya.schedulemicroservice.exception.city.NoSuchCityException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.partner.NoSuchPartnerException;
import com.ameya.schedulemicroservice.exception.showtime.ShowtimeAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.theater.NoSuchTheaterException;
import com.ameya.schedulemicroservice.exception.theater.TheaterAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.tier.NoSuchTierException;
import com.ameya.schedulemicroservice.exception.tier.TierAlreadyExistsException;
import com.ameya.schedulemicroservice.utils.OutputMessage;

public interface TheaterService {

	TheaterDto addTheater(TheaterDto theater)
			throws TheaterAlreadyExistsException, TierAlreadyExistsException, ShowtimeAlreadyExistsException,
			NoSuchMovieException, NoSuchTierException, NoSuchTheaterException, NoSuchCityException, NoSuchPartnerException;

	TheaterDto getTheaterById(int id) throws NoSuchTheaterException;

	List<TheaterDto> getAllTheaters();

	TheaterDto updateTheater(TheaterDto theaterDto) throws NoSuchTheaterException;

	OutputMessage deleteTheater(int id) throws NoSuchTheaterException;

}
