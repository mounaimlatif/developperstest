package com.jcdecaux.recruiting.developpers.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jcdecaux.recruiting.developpers.model.ProgrammingLanguageEntity;

@Repository
public interface  ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguageEntity, Integer>{
	
}
