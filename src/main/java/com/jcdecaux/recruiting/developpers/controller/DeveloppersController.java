package com.jcdecaux.recruiting.developpers.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.jcdecaux.recruiting.developpers.dto.DevelopperDTO;
import com.jcdecaux.recruiting.developpers.dto.ProgrammingLanguageDTO;
import com.jcdecaux.recruiting.developpers.exception.CreateElementsException;
import com.jcdecaux.recruiting.developpers.exception.ElementException;
import com.jcdecaux.recruiting.developpers.service.DevelopperService;
import com.jcdecaux.recruiting.developpers.service.ProgrammingLanguageService;
import com.jcdecaux.recruiting.developpers.validator.DeveloppersRequestValidator;
import com.jcdecaux.recruiting.developpers.validator.ProgrammingLanguagesRequestValidator;

@RestController
@RequestMapping("/api/0.1.0/")
public class DeveloppersController {
	

	Logger logger = LoggerFactory.getLogger(DeveloppersController.class);

	@Autowired
	private DevelopperService developperService;
	
	@Autowired
	private ProgrammingLanguageService programmingLanguageService;
	
	@Autowired
	private ProgrammingLanguagesRequestValidator createProgrammingLanguagesRequestValidator;

	@Autowired
	private DeveloppersRequestValidator developpersRequestValidator;

	@PostMapping(path = "/developpers/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<DevelopperDTO>> createDeveloppers(@RequestBody List<DevelopperDTO> developpers)
			throws CreateElementsException {
		logger.info("call /developpers/create ");
		developpersRequestValidator.validateCreateDeveloppers(developpers);
		return new ResponseEntity<>(
				developperService.createDeveloppers(Optional.of(developpers)), HttpStatus.CREATED);
	}
	@GetMapping(path = "/developpers")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<DevelopperDTO>> getDeveloppersByPL(@RequestParam(value="programminglanguage", required = true) Integer programmingLanguageId) throws ElementException {
		developpersRequestValidator.validateGetDeveloppersByPL(programmingLanguageId);
		return new ResponseEntity<>(developperService.getDeveloppers(programmingLanguageId), HttpStatus.OK);
	}
			
	@PutMapping(path = "/developpers/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DevelopperDTO> updateDevelopper(@PathVariable("id") Integer id , @RequestBody DevelopperDTO developper)
			throws ElementException {
		developpersRequestValidator.validateUpdateDevelopper(id, developper);
		return new ResponseEntity<> (developperService.updateDevelopper(id, developper),HttpStatus.OK);
	}
	
	@PutMapping(path = "/developpers/associate/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DevelopperDTO> associateDevelopper(@PathVariable("id") Integer id,@RequestParam("programminglanguage") Integer programmingLanguageId)
			throws ElementException {
		developpersRequestValidator.validateAssociateDevelopperWithPL(id, programmingLanguageId);
		return new ResponseEntity<> (developperService.associateDevelopperWithPL(id, programmingLanguageId),HttpStatus.OK);
	}

	@PostMapping(path = "/programminglanguages/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<ProgrammingLanguageDTO>> createProgramminglanguages(@RequestBody List<ProgrammingLanguageDTO> programminglanguages)
			throws CreateElementsException {
		createProgrammingLanguagesRequestValidator.validateCreateProgrammingLanguages(programminglanguages);
		return new ResponseEntity<>(
				programmingLanguageService.createProgrammingLangugages(programminglanguages), HttpStatus.CREATED);
	}

}
