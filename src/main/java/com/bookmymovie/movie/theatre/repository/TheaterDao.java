package com.bookmymovie.movie.theatre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookmymovie.movie.theatre.model.Theatre;

public interface TheaterDao extends JpaRepository<Theatre, Integer> {
		
	public List<Theatre> findByCity_Id(int id);
  //public Theatre findByScreens_Id(int screen_id);
	public Theatre findByCity_IdAndScreens_Id(int cityId, int screen_id);
	public Theatre findByScreens_Id(int screen_id);
}
