package kshopov.web.eprescription.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.lang.NonNull;

@Entity
public class Doctor extends BaseEntity {

	@Column(unique = true)
	@NonNull
	@NotEmpty
	@Email
	private String email;
	
	@OneToOne
	@ColumnDefault(value = "1")
	private TypeOfSender typeOfSender;
	
	@Size(min = 8, max = 10)
	@NotNull
	@NotEmpty
	private String uin;
	
	@Size(min = 8, max = 10)
	@NotNull
	@NotEmpty
	private String rcz;
	
	@Size(max = 500)
	@NotNull
	@NotEmpty
	private String lzName;
	
	@Size(min = 8, max = 30)
	@NotNull
	@NotEmpty
	private String password;
	
	@Size(min = 10, max = 15)
	@NotEmpty
	private String phone;
	
	@ColumnDefault(value = "0")
	private int isVerified;
	
	
	private String token;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUin() {
		return uin;
	}

	public void setUin(String uin) {
		this.uin = uin;
	}

	public String getRcz() {
		return rcz;
	}

	public void setRcz(String rcz) {
		this.rcz = rcz;
	}

	public String getLzName() {
		return lzName;
	}

	public void setLzName(String lzName) {
		this.lzName = lzName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TypeOfSender getTypeOfSender() {
		return typeOfSender;
	}

	public void setTypeOfSender(TypeOfSender typeOfSender) {
		this.typeOfSender = typeOfSender;
	}
	
}
