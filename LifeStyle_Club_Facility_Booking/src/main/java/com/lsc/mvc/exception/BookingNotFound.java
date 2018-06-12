package com.lsc.mvc.exception;

public class BookingNotFound extends Exception {
	private static final long serialVersionUID = 1L;
	public BookingNotFound() { };
	public BookingNotFound(String s) {
		super(s);
	}
}
