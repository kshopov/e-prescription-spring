package kshopov.web.eprescription.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdentifierType extends BaseEntity {
    private String description;
    private String descriptionEN;
    private String sinceVersion;
    private LocalDate validUntil;
}