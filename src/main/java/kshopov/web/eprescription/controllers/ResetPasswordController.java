package kshopov.web.eprescription.controllers;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.PasswordResetToken;
import kshopov.web.eprescription.repositories.PasswordResetTokenRepository;
import kshopov.web.eprescription.security.UserDetailsService;
import kshopov.web.eprescription.services.DoctorService;
import kshopov.web.eprescription.services.EmailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class ResetPasswordController {

    private final DoctorService doctorService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;
    private final UserDetailsService userDetailsService;


    public ResetPasswordController(DoctorService doctorService,
                                   PasswordResetTokenRepository passwordResetTokenRepository,
                                   EmailService emailService,
                                   UserDetailsService userDetailsService) {
        this.doctorService = doctorService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword()
    {
        return "forgotPassword";
    }

    @PostMapping("/resetPassword")
    public ModelAndView resetPassword(HttpServletRequest request,
                                      @RequestParam("email") String userEmail,
                                      RedirectAttributes redirectAttributes) {
        final Doctor doctor = doctorService.findDoctorByEmail(userEmail);

        if(doctor != null) {
            final String token = UUID.randomUUID().toString();
            final PasswordResetToken myToken = new PasswordResetToken(token, doctor);
            passwordResetTokenRepository.save(myToken);

            emailService.sendSimpleMessage(doctor.getEmail(), "Възстановяване на парола",
                    "На следния адрес може да възстановите Вашата парола " +
                            emailService.urlGenerator(request, "resetPassword", token));
        }

        redirectAttributes.addFlashAttribute("message",
                "На предоставения от вас email адрес е изпратена заявка за промяна на парола");
        return new ModelAndView("redirect:/login");
    }

    @GetMapping(value =  "/resetPassword")
    public ModelAndView resetPassword(@RequestParam("token") final String token, final RedirectAttributes attributes) {
        final Doctor doctor = passwordResetTokenRepository.findByToken(token).getDoctor();

        final Authentication auth = new UsernamePasswordAuthenticationToken(doctor, null,
                userDetailsService.loadUserByUsername(doctor.getEmail()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new ModelAndView("resetPassword");
    }

    @PostMapping("/savePassword")
    @ResponseBody
    public ModelAndView savePassword(@RequestParam("password") final String password,
                                     @RequestParam("passwordConfirmation") final String passwordConfirmation,
                                     final RedirectAttributes redirectAttributes) {
        final Doctor doctor = (Doctor) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        doctor.setPassword(password);
        doctor.setPasswordConfirmation(passwordConfirmation);
        doctorService.savePassword(doctor);

        redirectAttributes.addFlashAttribute("message", "Промяната на парола е успешна.");
        return new ModelAndView("redirect:/login");
    }

}
