package com.lsc.mvc.exception;

public class IssueNotFound extends Exception {
	private static final long serialVersionUID = 1L;
	public IssueNotFound() { };
	public IssueNotFound(String s) {
		super(s);
	}
}
