package com.bookmymovie.movie.theatre.model;

import java.util.ArrayList;
import java.util.List;

public class ScreenModel {

	private int screenId;
	private String screenName;
	private List<Show> shows = new ArrayList<>();
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
		
}
