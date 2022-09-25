package kshopov.web.eprescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kshopov.web.eprescription.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    
    PasswordResetToken findByToken(String token);

}
