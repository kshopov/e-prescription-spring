package kshopov.web.eprescription.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.validation.EmailExistsException;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private RegistrationController registrationController;
	
	@Mock
	BindingResult mockBindingResult;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	

	@Test
	void contextLoads() throws Exception {
		assertThat(registrationController).isNotNull();
	}
	
	@Test
	void shouldReturnRegisterPage() throws Exception {
		mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("Регистрация в E-рецепта")));
	}
	
	@Test
	void registerShouldReturnStatusOk_WithBindingResultErrors() throws Exception, EmailExistsException {
		mockMvc.perform(post("/register"))
			.andDo(print())
			.andExpect(model().attributeErrorCount("doctor", 0));
		
	}
	
	@Test
	void registerShouldReturnStatusOk_WithBindingResultWithoutErrors() throws Exception {
		when(mockBindingResult.hasErrors()).thenReturn(false);
		
		mockMvc.perform(post("/register")).andExpect(status().isOk());
	}

}
