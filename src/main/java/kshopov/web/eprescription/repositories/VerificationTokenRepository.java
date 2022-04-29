package kshopov.web.eprescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kshopov.web.eprescription.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	VerificationToken findByToken(String token);
	
}
