package com.bookmymovie.movie.theatre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmymovie.movie.theatre.model.Screen;

public interface ScreenDao extends JpaRepository<Screen, Integer>{

}
