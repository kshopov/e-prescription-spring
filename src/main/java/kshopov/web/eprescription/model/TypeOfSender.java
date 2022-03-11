package kshopov.web.eprescription.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TypeOfSender extends BaseEntity {

	private String description;
	private String descriptionEn;
	
	@OneToOne
	private Doctor doctor;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
