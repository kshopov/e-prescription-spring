package kshopov.web.eprescription.services;

import kshopov.web.eprescription.model.Patient;

public interface PatientService {
    
    Patient findPatientByIdentifier(String identifier);

    Patient findPatientById(Long id);

    Patient registerPatient(Patient patient); // throws new IdentifierExistsException

    Patient updatePatient(Patient patient);

    void deletePatient(Patient patient);
}
