package com.jcdecaux.recruiting.developpers.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.dao.ProgrammingLanguageRepository;
import com.jcdecaux.recruiting.developpers.dto.ProgrammingLanguageDTO;
import com.jcdecaux.recruiting.developpers.model.ProgrammingLanguageEntity;
import com.jcdecaux.recruiting.developpers.service.ProgrammingLanguageService;

@Service
@Transactional
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {

	@Autowired
	ProgrammingLanguageRepository programmingLanguageRepository;

	@Override
	public List<ProgrammingLanguageDTO> createProgrammingLangugages(List<ProgrammingLanguageDTO> programminglanguages) {
		List<ProgrammingLanguageEntity> programmingLanguageEntities = programminglanguages.stream()
				.map(d -> new ProgrammingLanguageEntity(d.getName())).collect(Collectors.toList());
		programmingLanguageRepository.saveAll(programmingLanguageEntities);
		return programmingLanguageEntities.stream().map(p -> new ProgrammingLanguageDTO(p.getId(), p.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProgrammingLanguageEntity> findProgrammingLanguageById(Integer id) {
		return programmingLanguageRepository.findById(id);

	}
}
