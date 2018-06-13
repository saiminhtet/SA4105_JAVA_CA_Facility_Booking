package com.lsc.mvc.validator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.lsc.mvc.model.User;

@Component
public class UserValidator implements Validator {
	
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("^65+(6|8|9)+\\d{7}");
	private static final Pattern VALID_PASSWORD_REGEX = Pattern
			.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!~*-,./?;:'])(?=\\S+$).{8,}$");

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User u = (User) target;

		// validate not null
		if (u.getUserNumber() == null) 		errors.rejectValue("userNumber", "userNumber.null", "User number cannot be null");
        if (u.getTitle() == null) 			errors.rejectValue("title", "title.null", "Title cannot be null");
	    if (u.getFirstName()== null) 		errors.rejectValue("firstName", "firstName.null", "First name cannot be null");
        if (u.getLastName() == null) 		errors.rejectValue("lastName", "lastName.null", "Last name cannot be null");
        if (u.getPassword() == null) 		errors.rejectValue("password", "password.null", "Password cannot be null");
        if (u.getEmailAddress() == null) 	errors.rejectValue("emailAddress", "emailAddress.null", "Email address cannot be null");
        if (u.getPhoneNumber() == null) 	errors.rejectValue("phoneNumber", "phoneNumber.null", "Phone number cannot be null");
        // validate not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userNumber", "userNumber.empty", "User number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "First name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Last name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "emailAddress.empty", "Email address cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "Phone number cannot be empty");

		// validate within range
		ArrayList<String> tList = new ArrayList<String>();
		tList.add("Dr"); tList.add("Mdm"); tList.add("Mr");tList.add("Mrs"); tList.add("Ms");
		if (!(tList.contains(u.getTitle()))) errors.rejectValue("title", "title.invalid", "Invalid title");

		// validate format
		Matcher passwordMatcher = VALID_PASSWORD_REGEX.matcher(u.getPassword());
		if (!passwordMatcher.find()) {
			errors.rejectValue("password", "password.invalid", "Invalid password format");
		}
		Matcher emailMatcher = VALID_EMAIL_ADDRESS_REGEX.matcher(u.getEmailAddress());
		if (!emailMatcher.find()) {
			errors.rejectValue("emailAddress", "emailAddress.invalid", "Invalid email address format");
		}
		Matcher phoneMatcher = VALID_PHONE_NUMBER_REGEX.matcher(u.getPhoneNumber());
		if (!phoneMatcher.find()) {
			errors.rejectValue("phoneNumber", "phoneNumber.invalid", "Invalid phone number format");
		}
	}
}
