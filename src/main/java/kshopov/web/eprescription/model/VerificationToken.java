package kshopov.web.eprescription.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class VerificationToken {
	
	public VerificationToken(String token, Doctor doctor) {
		this.token = token;
		this.doctor = doctor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String token;
	
	@OneToOne(targetEntity = Doctor.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "doctor_id")
	private Doctor doctor;
	
	private Date expiryDate;
	
}
