package com.jcdecaux.recruiting.developpers.exception;

import java.util.List;

public class CreateElementsException extends ElementException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<ElementException> elementsExceptions;
	
	public CreateElementsException(String customCode, String customMessage) {
		super(customCode, customMessage);
		elementsExceptions=null;
	}
	

	public CreateElementsException(String customCode, String customMessage,
			List<ElementException> elementsExceptions) {
		super(customCode, customMessage);
		this.elementsExceptions = elementsExceptions;
	}


	public List<ElementException> getElementsExceptions() {
		return elementsExceptions;
	}
	
	
}
