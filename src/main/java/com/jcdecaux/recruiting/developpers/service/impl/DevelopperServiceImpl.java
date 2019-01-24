package com.jcdecaux.recruiting.developpers.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.dao.DevelopperRepository;
import com.jcdecaux.recruiting.developpers.dto.DevelopperDTO;
import com.jcdecaux.recruiting.developpers.dto.ProgrammingLanguageDTO;
import com.jcdecaux.recruiting.developpers.exception.ElementException;
import com.jcdecaux.recruiting.developpers.exception.NotFoundElementException;
import com.jcdecaux.recruiting.developpers.model.DevelopperEntity;
import com.jcdecaux.recruiting.developpers.model.ProgrammingLanguageEntity;
import com.jcdecaux.recruiting.developpers.service.DevelopperService;
@Service
@Transactional
public class DevelopperServiceImpl implements DevelopperService {

	@Autowired
	DevelopperRepository developperRepository;

	@Autowired
	ProgrammingLanguageServiceImpl programmingLanguageService;

	@Override
	public List<DevelopperDTO> createDeveloppers(Optional<List<DevelopperDTO>> developpersDTO) {
		List<DevelopperEntity> developpersEntities = developpersDTO.get().stream()
				.map(d -> new DevelopperEntity(d.getFirstName(), d.getLastName())).collect(Collectors.toList());
		developperRepository.saveAll(developpersEntities);
		return developpersEntities.stream().map(e -> {
			DevelopperDTO dv = new DevelopperDTO();
			dv.setId(e.getId());
			dv.setLastName(e.getLastName());
			dv.setFirstName(e.getLastName());
			return dv;
		}).collect(Collectors.toList());
	}

	@Override
	public DevelopperDTO updateDevelopper(Integer id, DevelopperDTO developperDTO) throws ElementException {
		Optional<DevelopperEntity> developperToUpdate = developperRepository.findById(id);
		if (!developperToUpdate.isPresent())
			throw new ElementException("A04", " developper with id " + id + " doesn't exist");
		developperToUpdate.ifPresent(d -> {
			d.setFirstName(developperDTO.getFirstName());
			d.setLastName(developperDTO.getLastName());
			developperRepository.save(d);
		});
		DevelopperDTO dv = new DevelopperDTO();
		dv.setId(id);
		dv.setFirstName(dv.getFirstName());
		dv.setLastName(dv.getLastName());
		return dv;
	}

	@Override
	public DevelopperDTO associateDevelopperWithPL(Integer id, Integer programmingLanguageId) throws ElementException {
		Optional<DevelopperEntity> developperToUpdate = developperRepository.findById(id);
		if (!developperToUpdate.isPresent())
			throw new ElementException("A04", " developper with id " + id + " doesn't exist");
		Optional<ProgrammingLanguageEntity> programmingLangue = programmingLanguageService
				.findProgrammingLanguageById(programmingLanguageId);
		if (!programmingLangue.isPresent())
			throw new ElementException("A05",
					" Programming Langue  with id " + programmingLanguageId + " doesn't exist");
		DevelopperEntity developperEntity = developperToUpdate.get();
		developperEntity.setProgrammingLanguage(programmingLangue.get());
		developperRepository.save(developperEntity);
		return new DevelopperDTO(id, developperEntity.getFirstName(), developperEntity.getLastName(),
				new ProgrammingLanguageDTO(programmingLangue.get().getId(), programmingLangue.get().getName()));
	}

	@Override
	public List<DevelopperDTO> getDeveloppers(Integer programmingLanguageId) throws ElementException {
		Optional<ProgrammingLanguageEntity> programmingLangue = programmingLanguageService
				.findProgrammingLanguageById(programmingLanguageId);
		if (!programmingLangue.isPresent())
			throw new ElementException("A05",
					" Programming Langue  with id " + programmingLanguageId + " doesn't exist");
		Optional<List<DevelopperEntity>> list = Optional.ofNullable(
				developperRepository.findDevelopperEntityByProgrammingLanguageEntityId(programmingLanguageId));
		if (!list.isPresent() || list.get().isEmpty())
			throw new NotFoundElementException("N04", "  ");
		return list.get().stream().map(e -> {
			DevelopperDTO dv = new DevelopperDTO();
			dv.setId(e.getId());
			dv.setLastName(e.getLastName());
			dv.setFirstName(e.getLastName());
			return dv;
		}).collect(Collectors.toList());
	}

}
