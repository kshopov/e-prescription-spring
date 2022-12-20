package kshopov.web.eprescription.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.VerificationToken;
import kshopov.web.eprescription.repositories.PasswordResetTokenRepository;
import kshopov.web.eprescription.repositories.VerificationTokenRepository;
import kshopov.web.eprescription.security.UserDetailsService;
import kshopov.web.eprescription.services.DoctorService;
import kshopov.web.eprescription.services.EmailService;
import kshopov.web.eprescription.validation.EmailExistsException;

@Controller
public class RegistrationController {
	private final VerificationTokenRepository verificationTokenRepository;
	private final EmailService emailService;
	private final DoctorService doctorService;
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	private final UserDetailsService userDetailsService;
	
	public RegistrationController(DoctorService doctorService,
			VerificationTokenRepository verificationTokenRepository,
			EmailService emailService,
			PasswordResetTokenRepository passwordResetTokenRepository,
			UserDetailsService userDetailsService) {
		this.doctorService = doctorService;
		this.verificationTokenRepository = verificationTokenRepository;
		this.emailService = emailService;
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.userDetailsService = userDetailsService;
	}

	@GetMapping({"register", "register.html"})
	public String register(Doctor doctor) {
		return "index/register";
	}
	
	@PostMapping({"register"})
	public String register(@Valid Doctor doctor, 
			BindingResult bindingResult, final HttpServletRequest request) throws EmailExistsException {
		
		if(bindingResult.hasErrors()) {
			return "index/register";
		}

		try {
			doctor.setVerified(false);
			doctorService.registerNewUser(doctor);
			
			final String token = UUID.randomUUID().toString();
			final VerificationToken myToken = new VerificationToken(token, doctor);
			verificationTokenRepository.save(myToken);
	
			emailService.sendSimpleMessage(doctor.getEmail(), "Потвърждение на акаунт", 
					"На следния адрес може да потвърдите Вашия акаунт " +
							emailService.urlGenerator(request, "registrationConfirm", token));
		} catch (EmailExistsException e) {
			bindingResult.addError(new FieldError("doctor", "email", e.getMessage()));
			return "index/register";
		}

		return "redirect:/login";
	}

	@GetMapping(value = "registrationConfirm")
	public ModelAndView confirmRegistration(final Model model, 
		@RequestParam("token") final String token, 
		final RedirectAttributes redirectAttributes) {
			final VerificationToken verificationToken = doctorService.getVerificationToken(token);
			if(verificationToken == null) {
				redirectAttributes.addFlashAttribute("errorMessage", "Невалиден токен!");
				return new ModelAndView("redirect:/login");
			}

		final Doctor doctor = verificationToken.getDoctor();
			doctor.setVerified(true);
			doctorService.saveRegisteredUser(doctor);

			redirectAttributes.addFlashAttribute("message", "Акаунтът Ви е успешно потвърден!");

			return new ModelAndView("redirect:/login");
	}
}
