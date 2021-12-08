package com.bookmymovie.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmymovie.movie.theatre.model.City;

public interface CityDao extends JpaRepository<City, Integer> {
	
	public City findByName(String city);
}
