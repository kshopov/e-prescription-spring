package kshopov.web.eprescription.services;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
	
}
