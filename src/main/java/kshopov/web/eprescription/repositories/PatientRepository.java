package kshopov.web.eprescription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kshopov.web.eprescription.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
