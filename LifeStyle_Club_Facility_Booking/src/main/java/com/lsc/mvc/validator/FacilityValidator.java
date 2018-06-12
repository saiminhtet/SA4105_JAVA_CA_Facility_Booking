package com.lsc.mvc.validator;

import java.util.ArrayList;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lsc.mvc.model.Facility;

public class FacilityValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Facility.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Facility f = (Facility) target;
		// validate not null
		if (f.getFacilityName() == null) 		errors.rejectValue("facilityNumber", "facilityNumber.null", "Facility number cannot be null");
	    if (f.getFacilityType() == null) 		errors.rejectValue("facilityType", "facilityType.null", "Facility type cannot be null");
        if (f.getFacilityName() == null) 		errors.rejectValue("facilityName", "facilityName.null", "Facility name cannot be null");
        if (f.getFacilityDescription() == null) errors.rejectValue("facilityDescription", "facilityDescription.null", "Facility description cannot be null");
        if (f.getCapacity() == null)	 		errors.rejectValue("capacity", "capacity.null", "Capacity cannot be null");
        // validate not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityNumber", "facilityNumber.empty", "Facility number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityType", "facilityType.empty", "Facility type cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityName", "facilityName.empty", "Facility name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityDescription", "facilityDescription.empty", "Facility Description cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capacity", "capacity.empty", "Capacity cannot be empty");
		// validate within range
		ArrayList<String> tList = new ArrayList<String>();
		tList.add("Conference Room"); tList.add("Meeting Room"); tList.add("Karaoke Room");tList.add("Function Room");
		tList.add("Badminton Court"); tList.add("Squash Court"); tList.add("Tennis Court"); tList.add("Basketball Court");
		if (!(tList.contains(f.getFacilityType()))) errors.rejectValue("title", "title.invalid", "Invalid facility type");
		
		if (f.getCapacity() < 1) {
			errors.rejectValue("capacity", "capacity.invalid", "Facility capacity must be larger than 0");
		}
	}

}
