package com.bookmymovie.movie.theatre.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.movie.theatre.exception.CityNotFoundException;
import com.bookmymovie.movie.theatre.exception.TheatreNotFoundException;
import com.bookmymovie.movie.theatre.model.Theatre;
import com.bookmymovie.movie.theatre.model.TheatreModel;
import com.bookmymovie.movie.theatre.service.CityService;
import com.bookmymovie.movie.theatre.service.TheaterService;

@RestController
@RequestMapping("/api/theater")
public class ThearterController {

	@Autowired
	private TheaterService theaterService;
	
	@Autowired	
	private CityService cityService;
	
	@PostMapping("/add")
	public ResponseEntity<Theatre> addTheater(@RequestBody Theatre theatre){
		if(theatre ==null)
			throw new TheatreNotFoundException("Bad request : add theatre");
		if(theatre.getCity() == null)
			throw new CityNotFoundException("Bad request : Theatre should be associated with a city");
		
		cityService.addCity(theatre.getCity());
		return new ResponseEntity<Theatre>(theaterService.addTheatre(theatre),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{theatreId}")
	public void deleteTheater(@PathVariable int theatreId){		
		 theaterService.deleteTheatre(theatreId);		
	} 
	
	@GetMapping("/byId/{theatreId}")
	public ResponseEntity<Theatre> getTheaterById(@PathVariable int theatreId){
	   return new ResponseEntity<Theatre>(theaterService.getTheatreById(theatreId), HttpStatus.OK);	
	}
	
	@GetMapping("/byCity/{cityName}")
	public ResponseEntity<List<Theatre>> getTheatersByCity(@PathVariable String cityName){
	   return new ResponseEntity<List<Theatre>>(theaterService.getTheatresByCity(cityName), HttpStatus.OK);	
	}
	
	@GetMapping("/byMvoie/{cityId}/{movieId}/{showDate}")
	public ResponseEntity< Map<Integer,TheatreModel>> getTheatersByMvoie(@PathVariable int cityId, @PathVariable int movieId, @PathVariable("showDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate showDate){	
	   return new ResponseEntity< Map<Integer,TheatreModel>>(theaterService.getTheatresByMovie(movieId, cityId, showDate), HttpStatus.OK);	
	}
	
}
