package com.jcdecaux.recruiting.developpers.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DV_DEVELOPPER")
/**
 * 
 * @author Mounaim
 *  Entité qui représente un developpeurs 
 */
public class DevelopperEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;

	private String lastName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProgrammingLanguageEntity programmingLanguage;

	public DevelopperEntity() {
		
	}
	public DevelopperEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public int getId() {
		return id;
	}
	public ProgrammingLanguageEntity getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(ProgrammingLanguageEntity programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DevelopperEntity other = (DevelopperEntity) obj;
		if (id != other.id)
			return false;
		 return true;
	}
	
	
}
