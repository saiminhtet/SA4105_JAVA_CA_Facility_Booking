package com.lsc.mvc.exception;

public class FacilityNotFound extends Exception {
	private static final long serialVersionUID = 1L;
	public FacilityNotFound() { };
	public FacilityNotFound(String s) {
		super(s);
	}
}
