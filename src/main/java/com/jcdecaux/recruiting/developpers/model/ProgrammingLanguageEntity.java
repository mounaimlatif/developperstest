package com.jcdecaux.recruiting.developpers.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DV_PROGRAMMING_LANGUAGE")
/**
 * 
 * @author Mounaim
 * 
 *         Entité qui représente un langague de programmation
 *
 */
public class ProgrammingLanguageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@OneToMany(fetch = FetchType.LAZY , mappedBy = "programmingLanguage")
	private List<DevelopperEntity> developpers;

	public ProgrammingLanguageEntity() {
		super();
	}
	
	public ProgrammingLanguageEntity(String name) {
		super();
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

	public List<DevelopperEntity> getDeveloppers() {
		return developpers;
	}

	public void setDeveloppers(List<DevelopperEntity> developpers) {
		this.developpers = developpers;
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
		ProgrammingLanguageEntity other = (ProgrammingLanguageEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
