package com.lsc.mvc.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.service.FacilityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacilityValidatorTest {
	
	@Autowired
	private FacilityService fService;
	
	@Test
	public void testFacilityValidator() throws ResourceDefinitionInvalid {
		
		FacilityValidator fv = new FacilityValidator();
		Facility f = new Facility("Badminton Court", "Badminton Court 5", "This is a long description", 4);
//		Facility f = new Facility("Badminton Room", "Badminton Court 5", "", 0);
		fService.setNewFacNum(f);
		
		DataBinder binder = new DataBinder(f);
		binder.setValidator(fv);
		binder.validate();
		BindingResult results = binder.getBindingResult();
//		outputStringToConsole(results.toString());
		
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
