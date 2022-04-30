package kshopov.web.eprescription.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.VerificationToken;
import kshopov.web.eprescription.repositories.DoctorRepository;
import kshopov.web.eprescription.repositories.VerificationTokenRepository;
import kshopov.web.eprescription.services.DoctorService;
import kshopov.web.eprescription.services.EmailService;
import kshopov.web.eprescription.validation.EmailExistsException;

@Controller
public class IndexController implements WebMvcConfigurer{
	
	private final DoctorRepository doctorRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailService emailService;
	
	private final DoctorService doctorService;
	
	public IndexController(DoctorRepository doctorRepository, DoctorService doctorService,
			VerificationTokenRepository verificationTokenRepository,
			EmailService emailService) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
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
		
		doctor.setVerified(false);
		doctorService.registerNewUser(doctor);
		
		
		final String token = UUID.randomUUID().toString();
		final VerificationToken myToken = new VerificationToken(token, doctor);
		verificationTokenRepository.save(myToken);

		emailService.sendSimpleMessage(doctor.getEmail(), "test messsage", 
				"Това е тестово съобщение " + this.generateUrl());
		
		return "redirect:/login";
	}
	
	private String generateUrl() {
		return "url";
	}
	
}
