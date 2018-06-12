package com.lsc.mvc.exception;

public class UserNotFound extends Exception {
	private static final long serialVersionUID = 1L;
	public UserNotFound() { };
	public UserNotFound(String s) {
		super(s);
	}
}
