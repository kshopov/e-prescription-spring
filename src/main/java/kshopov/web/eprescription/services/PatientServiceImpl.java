package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.Patient;
import kshopov.web.eprescription.repositories.PatientRepository;

public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientById(Long id) {
        return null;
    }

    @Override
    public Patient findPatientByIdentifier(String identifier) {
        return null;
    }

    @Override
    public Patient registerPatient(Patient patient) {
        return null;
    }

    @Override
    public Patient updatePatient(Patient patient) {
        return null;
    }

    
    
}
