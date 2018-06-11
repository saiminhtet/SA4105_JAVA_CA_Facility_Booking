package com.lsc.mvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lsc.mvc.model.Booking;

public class BookingValidator implements Validator {

	private static final Pattern VALID_USER_NUMBER_REGEX = 
		    Pattern.compile("[A|M][0-9]{4}");
	private static final Pattern VALID_FACILITY_NUMBER_REGEX = 
		    Pattern.compile("[F][0-9]{3}");
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Booking.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Booking b = (Booking) target;
		// validate not null
		if (b.getBookingNumber() == null) errors.rejectValue("bookingNumber", "bookingNumber.null", "Booking number cannot be null");
	    if (b.getUserNumber()== null) errors.rejectValue("userNumber", "userNumber.null", "User number cannot be null");
        if (b.getFacilityNumber() == null) errors.rejectValue("facilityNumber", "facilityNumber.null", "Facility number cannot be null");
        if (b.getTransDateTime() == null) errors.rejectValue("transDateTime", "transDateTime.null", "Transcation date time cannot be null");
        if (b.getSlotDate() == null) errors.rejectValue("slotDate", "slotDate.null", "Slot date cannot be null");
        if (b.getSlotTimeStart() == null) errors.rejectValue("slotTimeStart", "slotTimeStart.null", "Slot start time cannot be null");
        if (b.getSlotTimeEnd() == null) errors.rejectValue("slotTimeEnd", "slotTimeEnd.null", "Slot end time cannot be null");
        // validate not empty
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookingNumber", "bookingNumber.empty", "Booking name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userNumber", "userNumber.empty", "User name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityNumber", "facilityNumber.empty", "Facility number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transDateTime", "transDateTime.empty", "Transaction date time cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slotDate", "slotDate.empty", "Slot date name cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slotTimeStart", "slotTimeStart.empty", "Slot start time cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "slotTimeEnd", "slotTimeEnd.empty", "Slot end time cannot be empty");
		// validate format
		Matcher userNumberMatcher = VALID_USER_NUMBER_REGEX.matcher(b.getUserNumber());
		if (!userNumberMatcher.find()) {
			errors.rejectValue("userNumber", "userNumber.invalid", "Invalid user name format");
		}
		Matcher facilityNumberMatcher = VALID_FACILITY_NUMBER_REGEX.matcher(b.getFacilityNumber());
		if (!facilityNumberMatcher.find()) {
			errors.rejectValue("facilityNumber", "facilityNumber.invalid", "Invalid facility name format");
		}
		// validate time
		if (b.getSlotDate().isBefore(b.getTransDateTime().toLocalDate())) {
			errors.rejectValue("slotDate", "slotDate.invalid", "Slot date cannot be earlier than transaction date");
		}
		if (b.getSlotTimeStart().compareTo("0900") < 0 || b.getSlotTimeStart().compareTo("2000") > 0) {
			errors.rejectValue("slotTimeStart", "slotTimeStart.invalid", "Slot start time must be between 0900 and 2000");
		}
		if (b.getSlotTimeEnd().compareTo(b.getSlotTimeStart()) <= 0) {
			errors.rejectValue("slotTimeEnd", "slotTimeEnd.invalid", "Slot start time must be earlier than slot end time");
		}
		if (b.getSlotTimeEnd().compareTo("1000") < 0 || b.getSlotTimeEnd().compareTo("2100") > 0) {
			errors.rejectValue("slotTimeEnd", "slotTimeEnd.invalid", "Slot end time must be between 1000 and 2100");
		}

	}

}
