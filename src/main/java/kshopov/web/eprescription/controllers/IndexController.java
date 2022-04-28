package kshopov.web.eprescription.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.repositories.DoctorRepository;
import kshopov.web.eprescription.services.DoctorService;
import kshopov.web.eprescription.validation.EmailExistsException;

@Controller
public class IndexController implements WebMvcConfigurer{
	
	private final DoctorRepository doctorRepository;
	
	private final DoctorService doctorService;
	
	public IndexController(DoctorRepository doctorRepository, DoctorService doctorService) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("successful_registration").setViewName("index/successful_registration");
	}
	
	@GetMapping({"", "/", "index", "index.html"})
	public String index() {
		return "index/index";
	}
	
	@GetMapping("/login")
	public String list() {
		return "loginPage";
	}
	
	@GetMapping({"register", "register.html"})
	public String register(Doctor doctor) {
		return "index/register";
	}
	
	@PostMapping({"register"})
	public String processRegistration(@Valid Doctor doctor, 
			BindingResult bindingResult) throws EmailExistsException {
		
		if(bindingResult.hasErrors()) {
			return "index/register";
		}
		
		doctorService.registerNewUser(doctor);
		
		return "redirect:/login";
	}
	
}
