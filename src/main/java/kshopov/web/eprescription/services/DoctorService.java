package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.Doctor;
import kshopov.web.eprescription.validation.EmailExistsException;

public interface DoctorService {
	
    Doctor registerNewUser(Doctor doctor) throws EmailExistsException;

    Doctor updateExistingUser(Doctor doctor) throws EmailExistsException;

}
