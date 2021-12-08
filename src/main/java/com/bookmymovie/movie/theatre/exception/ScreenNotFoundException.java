package com.bookmymovie.movie.theatre.exception;

public class ScreenNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076167178788035360L;

	public ScreenNotFoundException(String msg) {
		super(msg);
	}
}
