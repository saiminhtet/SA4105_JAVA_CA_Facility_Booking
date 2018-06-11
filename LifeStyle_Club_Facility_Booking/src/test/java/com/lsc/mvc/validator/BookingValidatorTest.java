package com.lsc.mvc.validator;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.service.BookingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingValidatorTest {
	
	@Autowired
	private BookingService bService;
	
	@Test
	public void testBookingValidator() throws ResourceDefinitionInvalid {
		// create validator
		BookingValidator bv = new BookingValidator();
		// create data to validate
		Booking b = new Booking("M0055", "F023", LocalDate.of(2018,6,15), "1000", "1200");
//		Booking b = new Booking("", "B023", LocalDate.of(2018,6,10), "1300", "1200");
		bService.setNewBookingNum(b);
		// create databinder to bind data and validator
		DataBinder binder = new DataBinder(b);
		binder.setValidator(bv);
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
