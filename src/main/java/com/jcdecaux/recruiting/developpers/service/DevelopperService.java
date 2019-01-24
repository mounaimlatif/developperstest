package com.jcdecaux.recruiting.developpers.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.jcdecaux.recruiting.developpers.dto.DevelopperDTO;
import com.jcdecaux.recruiting.developpers.exception.ElementException;

@Service
public interface DevelopperService {

	
	public List<DevelopperDTO> createDeveloppers(Optional<List<DevelopperDTO>> developpersDTO);

	public DevelopperDTO updateDevelopper(Integer id, DevelopperDTO developperDTO) throws ElementException;

	public DevelopperDTO associateDevelopperWithPL(Integer id, Integer programmingLanguageId)
			throws ElementException ;

	 public List<DevelopperDTO> getDeveloppers(Integer programmingLanguageId) throws ElementException;
}
