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
	
//	final static String USER_REQUIRED_FIELDS_BLANK = "Please fill up all the required information, and try again. ";
//	final static String USER_NEWPW_MISMATCH = "The new passwords do not match. Please try again.";
//	final static String USER_PW_INVALID = "The password provided is incorrect. Please try again.";
//	
//	final static String USER_PW_COMPLEXITY_NG = "The password should have at least 1 uppercase alphabet, "
//			+ "1 lowercase alphabet, 1 number and 1 symbol";
//	
//	final static String USER_SETPW_OK = "Successfully updated password. Thank you. ";
//	final static String USER_SETPW_NG = "Failed to update password. Please try again. ";
//	
//	final static String USER_SG_PHONENUM_NG = "Please use a Singapore registered phone number in the following format 65########. ";
//	final static String USER_USERNUM_INVALID = "Usernumber should be either M#### or A## patterns.";
	
//	public static Boolean isSGPhoneNumber(String phoneNum) {
//		// Working Code
//		
//		// Implementation: 
//		// if(isSGPhoneNumber(phoneNum)) { ... } else { ... }
//		
//		String regex = "[6][5][1-9][0-9]{7}";
//		return phoneNum.matches(regex);
//	}
//	
//	public static Boolean isUserNumber(String userNum) {
//		// Working Code
//		
//		// Implementation: 
//		// if(isUserNumber(userNum)) { ... } else { ... }
//		
//		String regexA = "[A][0-9]{2}";
//		String regexM = "[M][0-9]{4}";
//		
//		switch (userNum.substring(0,1)) {
//			case "A": return userNum.matches(regexA);
//			case "M": return userNum.matches(regexM);
//			default: return false;
//		}
//	}
//	
//	public static String userExist(String emailAdd, String phoneNum) {
//		// Dummy Code: return no error
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(userExist(emailAdd, phoneNumber)) { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that no blanks
//		
//		// Validate emailAdd
//		
//		// Validate phoneNum
//		
//		// Checks that no User object that matches either emailAdd or phoneNum
//		
//		return err;
//	}
//	public static Boolean isAdmin(String userNum) {
//		// Working Code
//		
//		// Example Implementation: 
//		// if(isAdmin(userNum)) { ... } else { ... }
//		
//		String regexA = "[A][0-9]{2}";
//		
//		switch (userNum.substring(0,1)) {
//			case "A": return userNum.matches(regexA);
//			default: return false;
//		}
//	}
//	
//	public static Boolean isSuperAdmin(String userNum) {
//		// Working Code
//		
//		// Example Implementation: 
//		// if(isSuperAdmin(userNum)) { ... } else { ... }
//		
//		if (userNum.equalsIgnoreCase("A01")) return true;
//		else return false;
//	}
//	
//	public static String validatePassword(String oldPw, String newPw, String confirmPw) {
//		// Dummy Code: return no error
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(validatePassword(oldPw, newPw, confirmPw)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that no blanks
//		
//		// Checks that newPw == confirmPw
//		
//		// Checks that oldPw is legitimate
//		
//		// Checks that newPw is complex
//		
//		return err;
//	}
//	
//	protected static String checkPwComplexity(String pw) {
//		// Dummy Code: return no error
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(checkPwComplexity(pw)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that at least 1 uppercase alphabet, 1 lowercase alphabet, 1 number, 1 symbol
//		
//		
//		return err;
//	}
//	
//	public static String setPassword(String userNum, String newPw) {
//		// Dummy Code: return success
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(setPassword(userNum, newPw)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that newPw is complex
//
//			// Try to update password
//
//			// Try to send email to user to notify about password update
//		
//		
//		return err;
//	}
//	
//	public static String validateEmail(String emailAdd) {
//		// Dummy code: return success
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(validateEmail(emailAdd)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that no blanks
//		
//		// Checks that emailAdd pattern matches user@domain pattern
//		
//		return err;
//	}
//	
//	public static String validatePhoneNumber(String phoneNum) {
//		// Dummy code: return success
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(validatePhoneNumber(phoneNum)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that no blanks
//		
//		// Checks that only numbers have been input
//		
//		// Checks that phoneNum pattern matches 65########
//		
//		return err;
//	}
//
//	public static User getUser(String userNum) {
//		// Dummy code: 1 Admin object hard-coded and returned as User
//		
//		// Example Implementation: 
//		// User u = getUser(userNum);
//		
//		// Checks that no blanks
//		
//		// Validate userNum
//		
//		// Get User object based on userNum
//		
//		Admin u = new Admin();
//		u.adminNumber = "A02";
//		u.title = "Mr";
//		u.firstName = "Bob";
//		u.lastName = "Tang";
//		u.middleName = "";
//		u.password = "password";
//		u.emailAddress = "bobtang@gmail.com";
//		u.phoneNumber = "6591234567";
//		return u;
//	}
//	
//	public static ArrayList<User> getMemberListByUserNumber(String userNum) {
//		// Dummy code: 1 Member object hard-coded and added to 1 ArrayList<User>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<User> uList = getMemberListByUserNumber(userNum);
//		
//		// Initialize ArrayList<User>
//		ArrayList<User> uList = new ArrayList<User>();
//		
//		// Validate userNum
//
//			// Gets Member object based on userNum
//			
//			
//			// Add Member object to ArrayList<User>
//		
//		
//		Member u1 = new Member();
//		u1.memberNumber = "M0001";
//		u1.title = "Mr";
//		u1.firstName = "Bob";
//		u1.lastName = "Tang";
//		u1.middleName = "";
//		u1.password = "password";
//		u1.emailAddress = "bobtang@gmail.com";
//		u1.phoneNumber = "6591234567";
//		uList.add(u1);
//		
//		return uList;
//	}
//	
//	public static ArrayList<User> getMemberListByName(String name) {
//		// Dummy code: 4 Member objects hard-coded and added to 1 ArrayList<User>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<User> uList = getMemberListByName(name);
//		
//		// Initialize ArrayList<User>
//		ArrayList<User> uList = new ArrayList<User>();
//
//		// Checks that name provided
//
//			// Gets all Member objects matching name criterion
//			// and add Member objects to ArrayList<User>
//			
//		
//		
//		Member u1 = new Member();
//		u1.memberNumber = "M0001";
//		u1.title = "Mr";
//		u1.firstName = "Bob";
//		u1.lastName = "Tang";
//		u1.middleName = "";
//		u1.password = "password";
//		u1.emailAddress = "bobtang@gmail.com";
//		u1.phoneNumber = "6591234567";
//		uList.add(u1);
//		
//		Member u2 = new Member();
//		u2.memberNumber = "M0002";
//		u2.title = "Mr";
//		u2.firstName = "Sam";
//		u2.lastName = "Teong";
//		u2.middleName = "Sneaky";
//		u2.password = "password";
//		u2.emailAddress = "samteong@gmail.com";
//		u2.phoneNumber = "6597654321";
//		uList.add(u2);
//		
//		Member u3 = new Member();
//		u3.memberNumber = "M0003";
//		u3.title = "Mr";
//		u3.firstName = "Jerome";
//		u3.lastName = "Kwek";
//		u3.middleName = "";
//		u3.password = "password";
//		u3.emailAddress = "jk@gmail.com";
//		u3.phoneNumber = "6581234567";
//		uList.add(u3);
//		
//		Member u4 = new Member();
//		u4.memberNumber = "M0004";
//		u4.title = "Mrs";
//		u4.firstName = "Dhanya";
//		u4.lastName = "Hunasavally";
//		u4.middleName = "Virupaksha";
//		u4.password = "password";
//		u4.emailAddress = "dhanya@gmail.com";
//		u4.phoneNumber = "6587654321";
//		uList.add(u4);
//		
//		return uList;
//	}
//	
//	public static ArrayList<User> getAdminList(String userNum, String name) {
//		// Dummy code: 4 Admin objects hard-coded and added to 1 ArrayList<User>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<User> uList = getAdminList(userNum, name);
//		
//		// Initialize ArrayList<User>
//		ArrayList<User> uList = new ArrayList<User>();
//		
//		// Validate userNum
//
//			// Gets Admin object based on userNum
//			
//			// Add Admin object to ArrayList<User>
//
//		// Checks that name provided
//		
//			// Gets all Admin objects matching name criterion
//			// and add Admin objects to ArrayList<User>
//			
//		
//		
//		Admin u1 = new Admin();
//		u1.adminNumber = "A01";
//		u1.title = "Mr";
//		u1.firstName = "Aaron";
//		u1.lastName = "Carter";
//		u1.middleName = "Benjamin";
//		u1.password = "password";
//		u1.emailAddress = "acarter@gmail.com";
//		u1.phoneNumber = "6591234567";
//		uList.add(u1);
//		
//		Admin u2 = new Admin();
//		u2.adminNumber = "A02";
//		u2.title = "Ms";
//		u2.firstName = "Jasmine";
//		u2.lastName = "Tay";
//		u2.middleName = "";
//		u2.password = "password";
//		u2.emailAddress = "jtay@gmail.com";
//		u2.phoneNumber = "6597654321";
//		uList.add(u2);
//		
//		Admin u3 = new Admin();
//		u3.adminNumber = "A03";
//		u3.title = "Mdm";
//		u3.firstName = "Suriya";
//		u3.lastName = "Wish";
//		u3.middleName = "";
//		u3.password = "password";
//		u3.emailAddress = "sw@gmail.com";
//		u3.phoneNumber = "6581234567";
//		uList.add(u3);
//		
//		Admin u4 = new Admin();
//		u4.adminNumber = "A04";
//		u4.title = "Mr";
//		u4.firstName = "Franken";
//		u4.lastName = "McDonald";
//		u4.middleName = "James";
//		u4.password = "password";
//		u4.emailAddress = "fmac@gmail.com";
//		u4.phoneNumber = "6587654321";
//		uList.add(u4);
//		
//		return uList;
//	}
//	
//	public static String updateUser
//		(String userNum, String title, String firstName, 
//		String lastName, String middleName, String pw, 
//		String emailAdd, String phoneNum) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(updateUser
//		// 		(String userNum, String title, String firstName, 
//		// 		String lastName, String middleName, String pw, 
//		// 		String emailAdd, String phoneNumber) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate userNum
//		
//		// Validate pw
//		
//		// Validate emailAdd
//		
//		// Validate phoneNum
//		
//		return err;
//	}
//	
//	public static String addUser
//	    (String title, String firstName, 
//	    String lastName, String middleName, String pw, 
//	    String emailAdd, String phoneNumber) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(addUser
//		// 		(String title, String firstName, 
//		// 		String lastName, String middleName, String pw, 
//		// 		String emailAdd, String phoneNumber) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate pw
//		
//		// Validate emailAdd
//		
//		// Validate phoneNum
//		
//		return err;
//	}
//	
//	public static String removeUser(String userNum) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(removeUser(userNum) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Validate userNum
//		
//		// Checks that userNum exists in database
//		
//		// Find User object
//		
//		// Remove User object
//		
//		return err;
//	}
//	
//	public static String validateLogin(String userNum, String pw) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(validateLogin(userNum, pw) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Validate userNum
//		
//		// Validate pw
//		
//		// Checks that userNum exists in database
//		
//		// Checks that pw corresponds to userNum
//		
//		return err;
//	}
}
