package com.jcdecaux.recruiting.developpers.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import com.jcdecaux.recruiting.developpers.dto.ProgrammingLanguageDTO;
import com.jcdecaux.recruiting.developpers.exception.CreateElementsException;
import com.jcdecaux.recruiting.developpers.exception.ElementException;

/**
 * 
 * @author Mounaim
 * 
 * TODO Valider les tailles et les formats des inputs pour  la sécuritè 
 *
 */
@Component
public class ProgrammingLanguagesRequestValidator {

	public void validateCreateProgrammingLanguages(List<ProgrammingLanguageDTO> programminglanguages)
			throws CreateElementsException {
		if (!Optional.ofNullable(programminglanguages).isPresent() || programminglanguages.isEmpty())
			throw new CreateElementsException("05", "empty or bad request");
		List<ElementException> errorsList = new ArrayList<>();
		programminglanguages.forEach(e -> validate(e).ifPresent(errorsList::add));
		if (!errorsList.isEmpty())
			throw new CreateElementsException("04", "Create Progamming Languages request error", errorsList);
	}

	public Optional<ElementException> validate(ProgrammingLanguageDTO programmingLanguageDTO) {
		if (!Optional.ofNullable(programmingLanguageDTO).isPresent())
			return Optional.of(new ElementException("A05", "Creation Progamming Language error : empty Progamming Language content "));
		if ( !Optional.ofNullable(programmingLanguageDTO.getName()).isPresent()
				|| programmingLanguageDTO.getName().length() < 1) {
			return Optional.of(new ElementException("A04",
					" Progamming Language  error :  name '" + programmingLanguageDTO.getName() + "' is invalid "));
		}
		return Optional.ofNullable(null);
	}

}