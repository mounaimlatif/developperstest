package com.jcdecaux.recruiting.developpers.exception;

public class ElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String customCode;
	private final String customMessage;

	public ElementException(String customCode, String customMessage) {
		super();
		this.customCode = customCode;
		this.customMessage = customMessage;
	}

	public String getCustomCode() {
		return customCode;
	}

	public String getCustomMessage() {
		return customMessage;
	}


}
