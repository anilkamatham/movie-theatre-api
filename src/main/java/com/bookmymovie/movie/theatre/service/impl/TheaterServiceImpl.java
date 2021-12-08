package com.bookmymovie.movie.theatre.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookmymovie.movie.theatre.exception.ScreenNotFoundException;
import com.bookmymovie.movie.theatre.exception.TheatreNotFoundException;
import com.bookmymovie.movie.theatre.model.City;
import com.bookmymovie.movie.theatre.model.Screen;
import com.bookmymovie.movie.theatre.model.ScreenModel;
import com.bookmymovie.movie.theatre.model.Show;
import com.bookmymovie.movie.theatre.model.Theatre;
import com.bookmymovie.movie.theatre.model.TheatreModel;
import com.bookmymovie.movie.theatre.repository.CityDao;
import com.bookmymovie.movie.theatre.repository.ScreenDao;
import com.bookmymovie.movie.theatre.repository.TheaterDao;
import com.bookmymovie.movie.theatre.service.TheaterService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterDao theaterDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private EntityManager entityManager;

	@Override
	public Theatre addTheatre(Theatre theater) {
		if (theater.getScreens() == null && theater.getScreens().size() < 0)
			throw new ScreenNotFoundException("Theater " + theater.getName() + " Should have at least one screen");
		return theaterDao.save(theater);
	}

	@Override
	public void deleteTheatre(int theatreId) {
		if (theaterDao.existsById(theatreId)) {
			theaterDao.deleteById(theatreId);
		} else
			throw new TheatreNotFoundException("Theatre does not exits. Enter valid theatre");
	}

	@Override
	public Theatre getTheatreById(int theatreId) {
		return theaterDao.findById(theatreId).orElseThrow(() -> new TheatreNotFoundException("Theatre Not found"));
	}

	@Override
	public List<Theatre> getTheatresByCity(String cityName) {
		City city = cityDao.findByName(cityName);
		return theaterDao.findByCity_Id(city.getId());
	}

	@Override
	public Map<Integer, TheatreModel> getTheatresByMovie(int movieId, int cityId, LocalDate showDate) {
		List<Show> shows = getAllShowByMovie(movieId,cityId,showDate);
		Map<Integer, TheatreModel> theatres = getAllTheatresByMovie(shows, cityId);
		return theatres;
	}

	private Map<Integer, TheatreModel> getAllTheatresByMovie(List<Show> shows, int cityId) {		
		Map<Integer, TheatreModel> theatreModels = new HashMap<>();		
		shows.stream().forEach(show1 -> {
			int screenId = show1.getScreenId();
			Theatre theatre = theaterDao.findByScreens_Id(screenId);
			theatre.getScreens().removeIf(s -> (s.getId() != screenId));		
			if (theatreModels.containsKey(theatre.getId())) {
				TheatreModel tm = theatreModels.get(theatre.getId());
				if (tm.getScreens().containsKey(screenId)) {
					tm.getScreens().get(screenId).getShows().add(show1);

				} else {
					ScreenModel sm = new ScreenModel();
					sm.setScreenId(screenId);
					sm.setScreenName(theatre.getScreens().get(0).getName());
					sm.getShows().add(show1);
					tm.getScreens().put(screenId, sm);
				}

			} else {
				TheatreModel tm = new TheatreModel();
				ScreenModel sm = new ScreenModel();
				sm.setScreenId(screenId);
				sm.setScreenName(theatre.getScreens().get(0).getName());		
				sm.getShows().add(show1);				
				tm.setTheatreId(theatre.getId());
				tm.setTheatreName(theatre.getName());
				tm.getScreens().put(screenId, sm);
				theatreModels.put(theatre.getId(), tm);
			}
			entityManager.clear();

		});

		return theatreModels;
	}

	private List<Show> getAllShowByMovie(int movieId, int cityId, LocalDate showDate) {
		ResponseEntity<String> response = restTemplate
				.getForEntity("http://localhost:8083/api/show/getShowsByMovie/" + movieId +"/"+cityId+"/"+showDate, String.class);
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		List<Show> shows = null;
		try {
			if (response.getStatusCode().is2xxSuccessful()) {
				shows = mapper.readValue(response.getBody(), new TypeReference<List<Show>>() {
				});

			}

		} catch (JsonParseException je) {

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return shows;
	}

}
