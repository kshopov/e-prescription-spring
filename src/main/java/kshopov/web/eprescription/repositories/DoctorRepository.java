package kshopov.web.eprescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kshopov.web.eprescription.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Doctor findByEmail(String email);

}
