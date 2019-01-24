package com.jcdecaux.recruiting.developpers.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.doThrow;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcdecaux.recruiting.developpers.controller.DeveloppersController;
import com.jcdecaux.recruiting.developpers.dto.DevelopperDTO;
import com.jcdecaux.recruiting.developpers.exception.CreateElementsException;
import com.jcdecaux.recruiting.developpers.service.DevelopperService;
import com.jcdecaux.recruiting.developpers.service.ProgrammingLanguageService;
import com.jcdecaux.recruiting.developpers.validator.DeveloppersRequestValidator;
import com.jcdecaux.recruiting.developpers.validator.ProgrammingLanguagesRequestValidator;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeveloppersController.class)
public class DeveloppersControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopperService developperService;

	@MockBean
	private ProgrammingLanguageService programmingLanguageService;

	@MockBean
	private ProgrammingLanguagesRequestValidator createProgrammingLanguagesRequestValidator;

	@MockBean
	private DeveloppersRequestValidator developpersRequestValidator;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 
	 * Success case 201
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCreateDeveloppersSuccessCase() throws Exception {

		List<DevelopperDTO> developpersList = new ArrayList<>();
		DevelopperDTO developperDTO = new DevelopperDTO();
		developperDTO.setFirstName("mounaim");
		developperDTO.setLastName("latif");
		developpersList.add(developperDTO);

		List<DevelopperDTO> developpersList2 = new ArrayList<>();
		DevelopperDTO developperDTO2 = new DevelopperDTO(1, "mounaim", "latif");
		developpersList2.add(developperDTO2);
		developperService.createDeveloppers(Optional.of(developpersList));
		given(developperService.createDeveloppers(Optional.of(developpersList))).willReturn(developpersList2);
		mockMvc.perform(post("/api/0.1.0//developpers/create").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(developpersList)).characterEncoding("utf-8")).andExpect(status().isCreated());
		;

	}

	/**
	 * bad request 400 empty content
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCreateDeveloppersFailedCase() throws Exception {
		List<DevelopperDTO> developpersList = new ArrayList<>();
		doThrow(new CreateElementsException("01", "empty or bad request")).when(developpersRequestValidator)
				.validateCreateDeveloppers(developpersList);
		mockMvc.perform(post("/api/0.1.0//developpers/create").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(developpersList)).characterEncoding("utf-8")).andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"code\":\"01\",\"message\":\"empty or bad request\"}")).andReturn();

	}
}