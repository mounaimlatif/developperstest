package com.jcdecaux.recruiting.developpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Mounaim
 *  DTO qui représente un developpeurs 
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DevelopperDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String firstName;
	private String lastName;
	private ProgrammingLanguageDTO programmingLanguage;
	
	public DevelopperDTO() {
		//empty
	}
	public DevelopperDTO(Integer id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public DevelopperDTO(Integer id, String firstName, String lastName, ProgrammingLanguageDTO programmingLanguage) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.programmingLanguage = programmingLanguage;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProgrammingLanguageDTO getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(ProgrammingLanguageDTO programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	

}
