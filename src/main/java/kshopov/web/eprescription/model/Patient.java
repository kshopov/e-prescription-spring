package kshopov.web.eprescription.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Patient extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identifier_id", referencedColumnName = "id")
    private IdentifierType identifierType;

    private String identifier;
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

    //CL001
    enum Gender {
        MALE, FEMALE, OTHER, UNKNOWN
    }

}
