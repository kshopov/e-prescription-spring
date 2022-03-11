package kshopov.web.eprescription.model;

import javax.persistence.Entity;

@Entity
public class Doctor extends BaseEntity {

	private String email;
//	private TypeOfSender typeOfSender;
	private String uin;
	private String rcz;
	private String lzName;
	private String password;
	private String phone;
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

}
