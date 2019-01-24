package com.jcdecaux.recruiting.developpers.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Mounaim
 * 
 *         DTO qui représente un langague de programmation
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProgrammingLanguageDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private List<DevelopperDTO> developpers;
	
	public ProgrammingLanguageDTO() {
		//empty
	}

	public ProgrammingLanguageDTO(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public List<DevelopperDTO> getDeveloppers() {
		return developpers;
	}

	public void setDeveloppers(List<DevelopperDTO> developpers) {
		this.developpers = developpers;
	}

}
