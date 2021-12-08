package com.bookmymovie.movie.theatre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.movie.theatre.model.City;
import com.bookmymovie.movie.theatre.repository.CityDao;
import com.bookmymovie.movie.theatre.service.CityService;

@Service
public class CityServiceImpl implements CityService{

	@Autowired
	private CityDao cityDao;
	
	@Override
	public City addCity(City city) {
		return cityDao.save(city);
	}

}
