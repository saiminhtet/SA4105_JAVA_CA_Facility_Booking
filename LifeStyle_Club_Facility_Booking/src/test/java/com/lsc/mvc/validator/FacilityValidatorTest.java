package com.lsc.mvc.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.service.FacilityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacilityValidatorTest {
	
	@Autowired
	private FacilityService fService;
	
	@Test
	public void testFacilityValidatorSuccess() throws ResourceDefinitionInvalid {
		// create validator
		FacilityValidator fv = new FacilityValidator();
		// create data to validate
		Facility f = new Facility("Badminton Court", "Badminton Court 5", "This is a long description", 4);
		try {
			fService.setNewFacNum(f);
		} catch (FacilityNotFound e) {
			outputStringToConsole(e.getMessage());
		}
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(f);
		binder.setValidator(fv);
		// validate data
		binder.validate();
		BindingResult results = binder.getBindingResult();
//		outputStringToConsole(results.toString());
		// check results
		if (results.hasErrors()) {
			throw new ResourceDefinitionInvalid();
		}
	}
	
	@Test
	public void testFacilityValidatorFailure() throws ResourceDefinitionInvalid {
		// create validator
		FacilityValidator fv = new FacilityValidator();
		// create data to validate
		Facility f = new Facility("Badminton Room", "Badminton Court 5", "", 0);
		try {
			fService.setNewFacNum(f);
		} catch (FacilityNotFound e) {
			outputStringToConsole(e.getMessage());
		}
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(f);
		binder.setValidator(fv);
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
