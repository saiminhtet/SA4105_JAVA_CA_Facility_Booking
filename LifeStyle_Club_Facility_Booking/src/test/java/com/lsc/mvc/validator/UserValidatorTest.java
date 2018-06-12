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
	public void testUserValidator() throws ResourceDefinitionInvalid {
		// create validator
		UserValidator uv = new UserValidator();
		
		// validation test expect pass
			// create data to validate
			User u1 = new User("Dr", "Strange", "Love", "Who", "Aa123456!", "1@1.com", "6592345678");
			try {
				uService.setNewUserNum(u1, "Member");
			} catch (UserNotFound e) {
				outputStringToConsole(e.getMessage());
			}
			
			// create databinder to bind data and validator
			DataBinder binder1 = new DataBinder(u1);
			binder1.setValidator(uv);
			
			// validate data
			binder1.validate();
			
			// check results
			BindingResult results1 = binder1.getBindingResult();
			outputStringToConsole(results1.toString());
			
			if (results1.hasErrors()) {
				throw new ResourceDefinitionInvalid();
			}
		
		// validation test expect fail
			// create data to validate	
			User u2 = new User("Drs", "Strange", "Love", "Who", "Aa123456", "1@1", "6512345678");
		
			// create databinder to bind data and validator
			DataBinder binder2 = new DataBinder(u2);
			binder2.setValidator(uv);
		
			// validate data
			binder2.validate();
			
			// check results
			BindingResult results2 = binder2.getBindingResult();
			outputStringToConsole(results2.toString());
			
			if (results2.hasErrors()) {
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
