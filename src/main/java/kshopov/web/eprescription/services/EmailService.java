package kshopov.web.eprescription.services;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);

	String urlGenerator(HttpServletRequest request, String url, String token);
}
