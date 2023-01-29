package kshopov.web.eprescription.controllers;

import kshopov.web.eprescription.model.Patient;
import kshopov.web.eprescription.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping({  "addpatient"})
    public String addPatientForm(Model model) {
        System.out.println("Get add patient");
        return "patient/addpatient";
    }

    @PostMapping({"addpatient"})
    public String addPatientSubmit(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patient/addpatient";
        }

        patientService.registerPatient(patient);

        return "redirect:/index";
    }

}
