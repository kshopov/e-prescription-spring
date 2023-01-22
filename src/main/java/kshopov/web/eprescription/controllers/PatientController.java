package kshopov.web.eprescription.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {

    @GetMapping({  "addpatient"})
    public String addPatient() {
        return "patient/addpatient";
    }

}
