package kshopov.web.eprescription.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Doctor extends BaseEntity {

	@Column(unique = true)
	@NonNull
	@NotEmpty(message = "{email.not.empty}")
	@Email(message = "{email.not.valid}")
	private String email;
	
	@Size(min = 8, max = 30, message = "{password.size}")
	@NotNull
	@NotEmpty(message = "{password.not.empty}")
	private String password;
	
	@Size(max = 500, message = "{lzName.max.size}")
	@NotNull
	@NotEmpty(message = "{lzName.not.empty}")
	private String lzName;
	
	@Size(min = 10, max = 10, message = "{rcz.size}")
	@NotNull
	@NotEmpty(message = "{rcz.not.empty}")
	private String rcz;
	
	
	@Size(min = 8, max = 10, message = "{uin.size}")
	@NotNull
	@NotEmpty(message = "{uin.not.empty}")
	private String uin;
	
	@Size(min = 10, max = 15, message = "{phone.size}")
	@NotEmpty(message = "{phone.not.empty}")
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private UserType typeOfSender;

	@ColumnDefault(value = "0")
	private int isVerified;

	private String token;
}
