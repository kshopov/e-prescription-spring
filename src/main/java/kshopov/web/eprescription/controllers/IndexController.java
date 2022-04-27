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

@Controller
public class IndexController implements WebMvcConfigurer{
	
	private final DoctorRepository doctorRepository;
	
	public IndexController(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("successful_registration").setViewName("index/successful_registration");
	}
	
	@GetMapping({"", "/", "index", "index.html"})
	public String index() {
		return "index/index";
	}
	
	@GetMapping({"register", "register.html"})
	public String register(Doctor doctor) {
		return "index/register";
	}
	
	@PostMapping({"process_register", "process_register.html"})
	public String processRegistration(@Valid Doctor doctor, 
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "index/register";
		}
		
		doctorRepository.save(doctor);
		
		return "redirect:/successful_registration";
	}
	
}
