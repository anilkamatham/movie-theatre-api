package com.bookmymovie.movie.theatre.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.bookmymovie.movie.theatre.model.Theatre;
import com.bookmymovie.movie.theatre.model.TheatreModel;

public interface TheaterService {

	public Theatre addTheatre(Theatre theater);
	public void deleteTheatre(int theatreId);
	public Theatre getTheatreById(int theatreId);
	public List<Theatre> getTheatresByCity(String cityName);
	public Map<Integer,TheatreModel> getTheatresByMovie(int movieId,int cityId, LocalDate showDate);
}
