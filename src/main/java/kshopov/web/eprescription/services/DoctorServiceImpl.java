package kshopov.web.eprescription.services;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.repositories.DoctorRepository;
import kshopov.web.eprescription.validation.EmailExistsException;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
    private final DoctorRepository repository;
    
    private final PasswordEncoder passwordEncoder;

	public DoctorServiceImpl(DoctorRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Doctor registerNewUser(Doctor doctor) throws EmailExistsException {
        if (emailExist(doctor.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + doctor.getEmail());
        }
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        return repository.save(doctor);
	}

	@Override
	public Doctor updateExistingUser(Doctor doctor) throws EmailExistsException {
		return null;
	}
	
    private boolean emailExist(String email) {
        final Doctor user = repository.findByEmail(email);
        return user != null;
    }
	
}