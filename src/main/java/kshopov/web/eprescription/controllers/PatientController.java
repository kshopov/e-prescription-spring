package kshopov.web.eprescription.controllers;

import kshopov.web.eprescription.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PatientController {

    @GetMapping({  "addpatient"})
    public String addPatientForm(Model model) {
        System.out.println("Get add patient");
        return "patient/addpatient";
    }

    @PostMapping({"addpatient"})
    public String addPatientSubmit(@Valid Patient patient,
                                   BindingResult bindingResult) {
        System.out.println("Post add patient");

        return "patient/addpatient";
    }

}
