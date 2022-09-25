package kshopov.web.eprescription.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	public static final String NO_REPLY_EMAIL = "noreplay@e-lekar.net";

	private final JavaMailSender emailSender;
	
	public EmailServiceImpl(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(NO_REPLY_EMAIL);
		message.setTo(to);
		message.setText(text);
		emailSender.send(message);
	}

}
