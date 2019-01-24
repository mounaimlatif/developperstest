package com.jcdecaux.recruiting.developpers.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jcdecaux.recruiting.developpers.model.DevelopperEntity;

@Repository
public interface  DevelopperRepository extends JpaRepository<DevelopperEntity, Integer>{
	
	@Query("select d from DevelopperEntity d where d.programmingLanguage.id = ?1")
	public List<DevelopperEntity> findDevelopperEntityByProgrammingLanguageEntityId(Integer id);
}
