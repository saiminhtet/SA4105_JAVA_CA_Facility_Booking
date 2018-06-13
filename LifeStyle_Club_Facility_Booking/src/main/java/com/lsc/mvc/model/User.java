package com.lsc.mvc.model;

import java.nio.charset.Charset;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User {
	
	// Entity Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@NotBlank
	@Column(name = "user_number")
	protected String userNumber;
	
	@NotBlank
	@Column(name = "title")
	protected String title;
	
	@NotBlank
	@Column(name = "first_name")
	protected String firstName;
	
	@NotBlank
	@Column(name = "last_name")
	protected String lastName;
	
	@Column(name = "middle_name")
	protected String middleName;
	
	@Column(name = "password")
	@ColumnTransformer(
	        read = "AES_DECRYPT(password, 'SA46Team6')",
	        write = "AES_ENCRYPT(?, 'SA46Team6')"
	)
	protected byte[] password;
	
	@NotBlank
	@Email(message = "Invalid Email")
	@Column(name = "email_address")
	protected String emailAddress;
	
	@NotBlank
	@Column(name = "phone_number")
	protected String phoneNumber;
	
	// Getters and Setters
	public Integer getUserId() { return userId; }
	public void setUserId(Integer userId) { this.userId = userId; }
	public String getUserNumber() { return userNumber; }
	public void setUserNumber(String userNumber) { this.userNumber = userNumber; }
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getMiddleName() { 
		if (middleName == "NA") middleName = ""; 
		return middleName; }
	public void setMiddleName(String middleName) { 
		if (middleName == null) middleName = "NA";
		this.middleName = middleName; }
	public String getPassword() { 
		String s = new String(password); 
		return s; }
	public void setPassword(String password) { 
		byte[] b = password.getBytes(Charset.forName("UTF-8"));
		this.password = b; }
	public String getEmailAddress() { return emailAddress; }
	public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	// Constructors	
	public User() { }
	public User(@NotBlank String title, @NotBlank String firstName,
			@NotBlank String lastName, String middleName, @NotBlank String password,
			@NotBlank String emailAddress, @NotBlank String phoneNumber) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		setMiddleName(middleName);
		setPassword(password);
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}
	
	// Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userNumber == null) ? 0 : userNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userNumber == null) {
			if (other.userNumber != null)
				return false;
		} else if (!userNumber.equals(other.userNumber))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", "
				+ "userNumber=" + userNumber + ", "
				+ "title=" + title + ", "
				+ "firstName=" + firstName + ", "
				+ "lastName=" + lastName + ", "
				+ "middleName=" + getMiddleName() + ", "
				+ "password=" + getPassword() + ", "
				+ "emailAddress=" + emailAddress + ", "
				+ "phoneNumber=" + phoneNumber + "]";
	}
}
