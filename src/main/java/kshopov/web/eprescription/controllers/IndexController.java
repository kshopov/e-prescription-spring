package kshopov.web.eprescription.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kshopov.web.eprescription.model.Doctor;

@Controller
public class IndexController implements WebMvcConfigurer{
	
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
	
	@PostMapping({"register", "register.html"})
	public String validateUserInfo(@Valid Doctor doctor, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "index/register";
		}
		
		return "redirect:/successful_registration";
	}
	
}
