package kshopov.web.eprescription.model;

import javax.persistence.Entity;

@Entity
public class TypeOfSender extends BaseEntity {

	private String description;
	private String descriptionEn;
	
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

}
