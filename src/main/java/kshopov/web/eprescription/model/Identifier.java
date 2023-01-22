package kshopov.web.eprescription.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Identifier extends BaseEntity {

    private String egn;
    private String lnz;
    private String ssn;
    private String pass;
    private String other;
    private String nbn; //новородено

}