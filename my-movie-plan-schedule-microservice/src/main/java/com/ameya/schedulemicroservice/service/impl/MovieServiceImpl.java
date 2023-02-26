package com.ameya.schedulemicroservice.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ameya.schedulemicroservice.dto.AddressDto;
import com.ameya.schedulemicroservice.dto.CityDto;
import com.ameya.schedulemicroservice.dto.GenreDto;
import com.ameya.schedulemicroservice.dto.LanguageDto;
import com.ameya.schedulemicroservice.dto.MovieDto;
import com.ameya.schedulemicroservice.dto.ScheduleDto;
import com.ameya.schedulemicroservice.dto.ShowtimeDto;
import com.ameya.schedulemicroservice.dto.TheaterDto;
import com.ameya.schedulemicroservice.dto.TierDto;
import com.ameya.schedulemicroservice.entity.Address;
import com.ameya.schedulemicroservice.entity.City;
import com.ameya.schedulemicroservice.entity.Genre;
import com.ameya.schedulemicroservice.entity.Language;
import com.ameya.schedulemicroservice.entity.Movie;
import com.ameya.schedulemicroservice.entity.Schedule;
import com.ameya.schedulemicroservice.entity.Showtime;
import com.ameya.schedulemicroservice.entity.Theater;
import com.ameya.schedulemicroservice.entity.Tier;
import com.ameya.schedulemicroservice.exception.ExceptionConstants;
import com.ameya.schedulemicroservice.exception.genre.NoSuchGenreException;
import com.ameya.schedulemicroservice.exception.language.NoSuchLanguageException;
import com.ameya.schedulemicroservice.exception.movie.MovieAlreadyExistsException;
import com.ameya.schedulemicroservice.exception.movie.NoSuchMovieException;
import com.ameya.schedulemicroservice.exception.schedule.NoSuchScheduleException;
import com.ameya.schedulemicroservice.repository.GenreRepository;
import com.ameya.schedulemicroservice.repository.LanguageRepository;
import com.ameya.schedulemicroservice.repository.MovieRepository;
import com.ameya.schedulemicroservice.service.MovieService;
import com.ameya.schedulemicroservice.service.ScheduleService;
import com.ameya.schedulemicroservice.utils.CrudMessage;
import com.ameya.schedulemicroservice.utils.OutputMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:MovieValidationMessages.properties")
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	LanguageRepository languageRepository;

	@Autowired
	ScheduleService scheduleService;

	@Autowired
	Environment env;

	@Override
	public MovieDto addMovie(MovieDto dto)
			throws MovieAlreadyExistsException, IOException, NoSuchGenreException, NoSuchLanguageException {

		Movie savedMovie = movieRepository.findByName(dto.getName());
		if (savedMovie != null) {
			throw new MovieAlreadyExistsException(env.getProperty(ExceptionConstants.MOVIE_ALREADY_EXISTS.toString()));
		}

		Movie movie = new Movie();
		movie.setName(dto.getName());
		movie.setDirectors(dto.getDirectors());
		movie.setCast(dto.getCast());
		movie.setReleaseDate(dto.getReleaseDate());
		movie.setDuration(dto.getDuration());
		movie.setPoster("");

		List<GenreDto> gdtos = dto.getGenres();
		List<Genre> genres = new ArrayList<>();
		for (GenreDto gdto : gdtos) {
			Genre g = genreRepository.findByName(gdto.getName());
			if(g == null) {
				throw new NoSuchGenreException(env.getProperty(ExceptionConstants.GENRE_NOT_FOUND.toString()));
			}
			genres.add(g);
		}
		movie.setGenres(genres);

		List<LanguageDto> ldtos = dto.getLanguages();
		List<Language> languages = new ArrayList<>();
		for (LanguageDto ldto : ldtos) {
			Language l = languageRepository.findById(ldto.getId()).orElseThrow(() -> new NoSuchLanguageException(
					env.getProperty(ExceptionConstants.LANGUAGE_NOT_FOUND.toString())));
			languages.add(l);
		}
		movie.setLanguages(languages);

		LocalDate today = LocalDate.now();

		if (dto.getReleaseDate().isBefore(today)) {
			movie.setActive(true);
		} else {
			movie.setActive(false);
		}

		movie.setSchedules(new ArrayList<Schedule>());

//		List<Genre> gens = movie.getGenres();
//
//		for (Genre g : gens) {
//			List<Movie> ms = g.getMovies();
//			ms.add(movie);
//			g.setMovies(ms);
//			genreRepository.save(g);
//		}
//
//		List<Language> ls = movie.getLanguages();
//		for (Language l : ls) {
//			List<Movie> ms = l.getMovies();
//			ms.add(movie);
//			l.setMovies(ms);
//			languageRepository.save(l);
//		}

		Movie saved = movieRepository.save(movie);

		MovieDto returnValue = dataTransfer(saved);

		return returnValue;
	}

	private MovieDto dataTransfer(Movie movie) {
		MovieDto dto = new MovieDto();
		dto.setId(movie.getId());
		dto.setName(movie.getName());
		dto.setDirectors(movie.getDirectors());
		dto.setCast(movie.getCast());
		dto.setDuration(movie.getDuration());
		dto.setReleaseDate(movie.getReleaseDate());
		dto.setActive(movie.isActive());
		dto.setPoster(movie.getPoster());
		List<Genre> genres = movie.getGenres();
		List<GenreDto> gdtos = new ArrayList<>();
		for (Genre g : genres) {
			GenreDto gdto = new GenreDto();
			gdto.setId(g.getId());
			gdto.setName(g.getName());
			gdtos.add(gdto);
		}
		List<Language> languages = movie.getLanguages();
		List<LanguageDto> ldtos = new ArrayList<>();
		for (Language l : languages) {
			LanguageDto ldto = new LanguageDto();
			ldto.setId(l.getId());
			ldto.setName(l.getName());
			ldtos.add(ldto);
		}
		dto.setGenres(gdtos);
		dto.setLanguages(ldtos);

		List<Schedule> schedules = movie.getSchedules();
		List<ScheduleDto> sdtos = new ArrayList<>();
		for (Schedule s : schedules) {
			ScheduleDto sdto = new ScheduleDto();
			sdto.setId(s.getId());

			Theater t = s.getTheater();
			Showtime st = s.getShowtime();

			TheaterDto tdto = new TheaterDto();
			tdto.setId(t.getId());
			Address a = t.getAddress();
			City c = t.getCity();
			CityDto cdto = new CityDto();
			cdto.setId(c.getId());
			cdto.setName(c.getName());
			tdto.setCity(cdto);
			AddressDto adto = new AddressDto();
			adto.setCityDto(cdto);
			adto.setId(a.getId());
			adto.setLine1(a.getLine1());
			adto.setLine2(a.getLine2());
			adto.setPincode(a.getPincode());
			tdto.setAddress(adto);
			List<Tier> tiers = t.getTiers();
			List<TierDto> tierDtos = new ArrayList<>();
			for (Tier tier : tiers) {
				TierDto tierDto = new TierDto();
				tierDto.setId(tier.getId());
				tierDto.setName(tier.getName());
				tierDto.setPrice(tier.getPrice());
				tierDto.setPriority(tier.getPriority());
				tierDto.setNoOfSeats(tier.getNoOfSeats());
				tierDto.setSeatsBooked(tier.getSeatsBooked());
				tierDto.setRows(tier.getRows());
				tierDto.setCols(tier.getCols());
				tierDtos.add(tierDto);
			}
			tdto.setTiers(tierDtos);
			sdto.setTheater(tdto);

			ShowtimeDto stdto = new ShowtimeDto();
			stdto.setId(st.getId());
			stdto.setTime(st.getTime());
			sdto.setShowtime(stdto);
		}
		dto.setSchedules(sdtos);
		return dto;
	}

	@Override
	public MovieDto getMovieById(int id) throws NoSuchMovieException {

		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		MovieDto mdto = dataTransfer(movie);
		return mdto;
	}

	@Override
	public List<MovieDto> getAllMovies() {

		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			if (m.isActive()) {
				MovieDto mdto = dataTransfer(m);
				movieDtos.add(mdto);
			}
		}
		return movieDtos;
	}

	@Override
	public List<MovieDto> getAllMoviesAdmin() {

		List<Movie> movies = (List<Movie>) movieRepository.findAll();
		List<MovieDto> movieDtos = new ArrayList<>();
		for (Movie m : movies) {
			MovieDto mdto = dataTransfer(m);
			movieDtos.add(mdto);
		}
		return movieDtos;

	}

	@Override
	public MovieDto updateMovie(MovieDto dto) throws NoSuchMovieException {
		Movie movie = movieRepository.findById(dto.getId()).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		if (dto.getName() != null && !dto.getName().equals(movie.getName())) {
			movie.setName(dto.getName());
		}
		if (dto.getDirectors() != null && !dto.getDirectors().equals(movie.getDirectors())) {
			movie.setDirectors(dto.getDirectors());
		}
		if (dto.getCast() != null && !dto.getCast().equals(movie.getCast())) {
			movie.setCast(dto.getCast());
		}
		if (dto.getDuration() != null && !dto.getDuration().equals(movie.getDuration())) {
			movie.setDuration(dto.getDuration());
		}
		if (dto.getReleaseDate() != null && !dto.getReleaseDate().equals(movie.getReleaseDate())) {
			movie.setReleaseDate(dto.getReleaseDate());
		}
		if (dto.getPoster() != null && !dto.getPoster().equals(movie.getPoster())) {
			movie.setPoster(dto.getPoster());
		}
		Movie saved = movieRepository.save(movie);

		return dataTransfer(saved);
	}

	@Override
	public OutputMessage deleteMovie(int id) throws NoSuchMovieException, NoSuchScheduleException {

		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));

		List<Schedule> schedules = movie.getSchedules();
		if (schedules != null) {
			for (Schedule s : schedules) {
				OutputMessage o = scheduleService.deleteSchedule(s.getId());
			}
		}

		movieRepository.delete(movie);

		String fileName = movie.getPoster().substring(movie.getPoster().lastIndexOf("\\"));

		OutputMessage om = new OutputMessage();
		om.setMessage(env.getProperty(CrudMessage.MOVIE_DELETE_SUCCESS.toString()));

		return om;
	}

	@Override
	public MovieDto updateStatus(int id) throws NoSuchMovieException, NoSuchScheduleException {
		Movie movie = movieRepository.findById(id).orElseThrow(
				() -> new NoSuchMovieException(env.getProperty(ExceptionConstants.MOVIE_NOT_FOUND.toString())));
		movie.setActive(!movie.isActive());
		if (!movie.isActive()) {
			List<Schedule> schedules = movie.getSchedules();
			if (schedules != null) {
				for (Schedule s : schedules) {
					OutputMessage om = scheduleService.deleteSchedule(s.getId());
				}
			}
			movie.setSchedules(new ArrayList<>());
		}
		Movie saved = movieRepository.save(movie);
		MovieDto returnValue = dataTransfer(saved);
		return returnValue;
	}

}
