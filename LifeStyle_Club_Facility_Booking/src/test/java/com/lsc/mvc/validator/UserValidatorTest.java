package com.lsc.mvc.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserValidatorTest {
	
	@Autowired
	private UserService uService;
	
	@Test
	public void testUserValidatorSuccess() throws ResourceDefinitionInvalid {
		// create validator
		UserValidator uv = new UserValidator();
		
		// create data to validate
		User u = new User("Dr", "Strange", "Love", "Who", "Aa123456!", "1@1.com", "6592345678");
		try {
			uService.setNewUserNum(u, "Member");
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
			
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(u);
		binder.setValidator(uv);
			
		// validate data
		binder.validate();
			
		// check results
		BindingResult results = binder.getBindingResult();
//		outputStringToConsole(results.toString());
			
		if (results.hasErrors()) {
			throw new ResourceDefinitionInvalid();
		}		
	}
	
	@Test
	public void testUserValidatorFailure() throws ResourceDefinitionInvalid {
		
		// create validator
		UserValidator uv = new UserValidator();
				
		// create data to validate	
		User u = new User("Drs", "Strange", "Love", "Who", "Aa123456", "1@1", "6512345678");
		try {
			uService.setNewUserNum(u, "Member");
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
			
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(u);
		binder.setValidator(uv);
			
		// validate data
		binder.validate();
				
		// check results
		BindingResult results = binder.getBindingResult();
//		outputStringToConsole(results.toString());
				
		if (results.hasErrors()) {
			throw new ResourceDefinitionInvalid();
		}
	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
