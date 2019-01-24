package com.jcdecaux.recruiting.developpers.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jcdecaux.recruiting.developpers.dto.ErrorDTO;
import com.jcdecaux.recruiting.developpers.dto.ErrorDetailDTO;
import com.jcdecaux.recruiting.developpers.exception.CreateElementsException;
import com.jcdecaux.recruiting.developpers.exception.ElementException;


@ControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(CreateElementsException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDTO exception(CreateElementsException e) {
		ErrorDTO errors = new ErrorDTO(e.getCustomCode() , e.getCustomMessage() );
		Optional.ofNullable(e.getElementsExceptions()).ifPresent(list -> errors.setDetails(list.stream().map( ex -> new ErrorDetailDTO(ex.getCustomCode(),ex.getCustomMessage())).collect(Collectors.toList())));
		
		return errors;
	}
	
	@ExceptionHandler(ElementException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDTO exception(ElementException e) {
		return new ErrorDTO(e.getCustomCode() , e.getCustomMessage() );
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorDTO exception(HttpMessageNotReadableException e) {
		return new ErrorDTO(HttpStatus.BAD_REQUEST.toString() , e.getMessage() );
	}
	
	
}