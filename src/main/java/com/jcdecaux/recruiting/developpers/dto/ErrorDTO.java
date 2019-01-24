package com.jcdecaux.recruiting.developpers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDTO extends ErrorDetailDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorDTO(String code, String message) {
		super(code, message);
	}

	private List<ErrorDetailDTO> details;

	public List<ErrorDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<ErrorDetailDTO> details) {
		this.details = details;
	}

}
