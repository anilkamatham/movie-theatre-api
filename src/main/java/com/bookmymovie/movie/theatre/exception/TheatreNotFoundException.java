package com.bookmymovie.movie.theatre.exception;

public class TheatreNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -856416529162730640L;

	public TheatreNotFoundException(String msg) {
         super(msg);
	}
}
