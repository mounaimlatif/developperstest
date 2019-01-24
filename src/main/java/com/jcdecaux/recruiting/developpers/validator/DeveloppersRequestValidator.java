package com.jcdecaux.recruiting.developpers.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.jcdecaux.recruiting.developpers.dto.DevelopperDTO;
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
public class DeveloppersRequestValidator {

	Logger logger = LoggerFactory.getLogger(DeveloppersRequestValidator.class);
	
	public void validateCreateDeveloppers(List<DevelopperDTO> developpers) throws CreateElementsException {
		if (!Optional.ofNullable(developpers).isPresent() || developpers.isEmpty()) {
			logger.error("empty developpers list {} ",developpers);
			throw new CreateElementsException("01", "empty or bad request");
		}
		List<ElementException> errorsList = new ArrayList<>();
		developpers.forEach(e -> validate(e).ifPresent(errorsList::add));
		if (!errorsList.isEmpty())
			throw new CreateElementsException("02", "Create Developpers request error", errorsList);
	}

	public Optional<ElementException> validate(DevelopperDTO developperDTO) {
		if (Optional.ofNullable(developperDTO).isPresent()) {
			if (!Optional.ofNullable(developperDTO.getFirstName()).isPresent()
					|| developperDTO.getFirstName().length() < 3) {
				return Optional.of(new ElementException("A02",
						" Developper error :  firstName '" + developperDTO.getFirstName() + "' is invalid "));
			}
			if (!Optional.ofNullable(developperDTO.getLastName()).isPresent()
					|| developperDTO.getLastName().length() < 3) {
				return Optional.of(new ElementException("A03",
						" Developper error :  firstName '" + developperDTO.getLastName() + "' invalid "));
			}
			return Optional.ofNullable(null);
		} 
		return Optional.ofNullable(new ElementException("A01", "Creation Developper error : empty develpper content "));
	}

	public void validateUpdateDevelopper(Integer id, DevelopperDTO developperDTO) throws ElementException {
		if (!Optional.ofNullable(id).isPresent())
			throw new ElementException("A06", "Invalide id : "+id);
		validate(developperDTO);
	}

	public void validateAssociateDevelopperWithPL(Integer id, Integer programmingLanguageId) throws ElementException {
		if (!Optional.ofNullable(id).isPresent())
			throw new ElementException("A06", "Invalide id : "+id);
		if (!Optional.ofNullable(programmingLanguageId).isPresent())
			throw new ElementException("A07", "Invalide programmingLanguageId : "+programmingLanguageId);
	}

	public void validateGetDeveloppersByPL(Integer programmingLanguageId) throws ElementException {
		if (!Optional.ofNullable(programmingLanguageId).isPresent())
			throw new ElementException("A06", "Invalide id : "+programmingLanguageId);
	}

}