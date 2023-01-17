package kshopov.web.eprescription.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Patient extends BaseEntity {
    
    private IdentifierType indentifierType;
    private String indetifier;
    private String nhifInsuranceNumber;
    private Date birthDate;
    private Gender gender;
    private String prBookNumber;
    private String given;
    private String middle;
    private String family;
    private String country;
    private String county;
    private String city;
    private String line;
    private String postalCode;
    private String phone;
    private String email; 
    private double age;
    private double weight;
    private boolean isPregnant;
    private boolean isBreastFeeding;

    //CL004
    enum IdentifierType {
        EGN, LNZ, SSN,
        PASS, OTHER, NBN
    }

    //CL001
    enum Gender {
        MALE, FEMALE, OTHER, UNKNOWN
    }

}
