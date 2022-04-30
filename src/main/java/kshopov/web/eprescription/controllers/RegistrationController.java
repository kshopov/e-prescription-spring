package kshopov.web.eprescription.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.VerificationToken;
import kshopov.web.eprescription.repositories.DoctorRepository;
import kshopov.web.eprescription.repositories.VerificationTokenRepository;
import kshopov.web.eprescription.services.DoctorService;
import kshopov.web.eprescription.services.EmailService;
import kshopov.web.eprescription.validation.EmailExistsException;

@Controller
public class RegistrationController {
	
	private final DoctorRepository doctorRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailService emailService;
	
	private final DoctorService doctorService;
	
	public RegistrationController(DoctorRepository doctorRepository, DoctorService doctorService,
			VerificationTokenRepository verificationTokenRepository,
			EmailService emailService) {
		this.doctorRepository = doctorRepository;
		this.doctorService = doctorService;
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
	}

	@GetMapping({"register", "register.html"})
	public String register(Doctor doctor) {
		return "index/register";
	}
	
	@PostMapping({"register"})
	public String processRegistration(@Valid Doctor doctor, 
			BindingResult bindingResult, final HttpServletRequest request) throws EmailExistsException {
		
		if(bindingResult.hasErrors()) {
			return "index/register";
		}
		
		doctor.setVerified(false);
		doctorService.registerNewUser(doctor);
		
		
		final String token = UUID.randomUUID().toString();
		final VerificationToken myToken = new VerificationToken(token, doctor);
		verificationTokenRepository.save(myToken);

		emailService.sendSimpleMessage(doctor.getEmail(), "Потвърждение на акаунт", 
				"На следния адрес може да потвърдите Вашия акаунт " + this.generateUrl(request));
		
		return "redirect:/login";
	}
	
	private String generateUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
