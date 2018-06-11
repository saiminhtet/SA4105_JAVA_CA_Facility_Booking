package com.lsc.mvc.validator;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.lsc.mvc.exception.IssueNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.model.Issue;
import com.lsc.mvc.service.IssueService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IssueValidatorTest {
	
	@Autowired
	private IssueService iService;
	
	@Test
	public void testIssueValidator() throws ResourceDefinitionInvalid {
		// create validator
		IssueValidator iv = new IssueValidator();
		// create data to validate
		Issue i = new Issue("A0002", "F023", LocalDateTime.of(2018, 6, 13, 12, 00), "Some thing wrong", "Open");
//		Issue i = new Issue("Z0002", "", LocalDateTime.of(2018, 6, 15, 12, 00), "Some thing wrong", "Close");
		try {
			iService.setNewIssueNum(i);
		} catch (IssueNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(i);
		binder.setValidator(iv);
		// validate data
		binder.validate();
		BindingResult results = binder.getBindingResult();
//		outputStringToConsole(results.toString());
		// check results
		if (results.hasErrors()) {
			throw new ResourceDefinitionInvalid();
		}
	}
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}


}
