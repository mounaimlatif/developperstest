package com.jcdecaux.recruiting.developpers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.dto.ProgrammingLanguageDTO;
import com.jcdecaux.recruiting.developpers.model.ProgrammingLanguageEntity;

@Service
public interface ProgrammingLanguageService {

	public List<ProgrammingLanguageDTO> createProgrammingLangugages(List<ProgrammingLanguageDTO> programminglanguages);

	public Optional<ProgrammingLanguageEntity> findProgrammingLanguageById(Integer id);
}
