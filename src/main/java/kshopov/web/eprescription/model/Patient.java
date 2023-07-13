package kshopov.web.eprescription.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import kshopov.web.eprescription.validation.IdentifierConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patient extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identifier_id", referencedColumnName = "id")
    @NotNull(message = "Въвели сте некоректно стойност за тип на идентификатор!")
    private IdentifierType identifierType;

    @NotBlank
    @IdentifierConstraint
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
