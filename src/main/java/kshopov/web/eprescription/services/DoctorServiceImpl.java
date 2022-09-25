package kshopov.web.eprescription.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.model.VerificationToken;
import kshopov.web.eprescription.repositories.DoctorRepository;
import kshopov.web.eprescription.repositories.VerificationTokenRepository;
import kshopov.web.eprescription.security.PasswordEncoder;
import kshopov.web.eprescription.validation.EmailExistsException;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	
    private final DoctorRepository doctorRepository;
    
    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenRepository verificationTokenRepo;

	public DoctorServiceImpl(DoctorRepository repository, 
                            PasswordEncoder passwordEncoder,
                            VerificationTokenRepository verificationTokenRepo) {
		this.doctorRepository = repository;
		this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
	}

	@Override
	public Doctor registerNewUser(Doctor doctor) throws EmailExistsException {
        if (emailExist(doctor.getEmail())) {
            throw new EmailExistsException("Вече има създаден акаунт с този email адрес: " + doctor.getEmail());
        }
        doctor.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
	}

	@Override
	public Doctor updateExistingUser(Doctor doctor) throws EmailExistsException {
		return null;
	}
    
    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepo.findByToken(token);
    }

    @Override
    public void saveRegisteredUser(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public Doctor findDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }
	
    private boolean emailExist(String email) {
        final Doctor user = doctorRepository.findByEmail(email);
        return user != null;
    }



}