package com.cedricleumaleu.accessorassessmenttest.exceptions;

public class CsvException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CsvException(String message, Throwable t) {
		super(message, t);
	}
}
