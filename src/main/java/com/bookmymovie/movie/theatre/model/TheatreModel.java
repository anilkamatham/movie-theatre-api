package com.bookmymovie.movie.theatre.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class TheatreModel {

	private int theatreId;
	private String theatreName;
	private Map<Integer, ScreenModel> screens = new HashMap<>();
	
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public Map<Integer, ScreenModel> getScreens() {
		return screens;
	}
	public void setScreens(Map<Integer, ScreenModel> screens) {
		this.screens = screens;
	}
			
}
