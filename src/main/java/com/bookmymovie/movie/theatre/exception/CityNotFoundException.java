package com.bookmymovie.movie.theatre.exception;

public class CityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7970624905732758847L;

	public CityNotFoundException(String msg) {
		super(msg);
	}
}
