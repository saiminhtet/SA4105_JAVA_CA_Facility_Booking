package com.lsc.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.lsc.mvc.model.User;

@Component
public class UserValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User u = (User) target;
		ValidationUtils.rejectIfEmpty(errors, "title", "error.user.title.empty");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "error.user.firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "error.user.lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.user.password.empty");
		ValidationUtils.rejectIfEmpty(errors, "emailAddress", "error.user.emailAddress.empty");
		ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "error.user.phoneNumber.empty");
		System.out.println(u.toString());
	}
}
