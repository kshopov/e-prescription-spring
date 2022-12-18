package kshopov.web.eprescription.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class PersistentLogins extends BaseEntity {
    
    private String username;
    private String series;
    private String token;
    private Date lastUsed;   

}
