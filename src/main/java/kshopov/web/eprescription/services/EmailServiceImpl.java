package kshopov.web.eprescription.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public String urlGenerator(HttpServletRequest request, String url, String token) {
		 return "http://" + request.getServerName()
				+ ":" + request.getServerPort()
				+ request.getContextPath()
				+ "/" + url + "?token="
				+ token;
	}
}
