package com.lsc.mvc.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lsc.mvc.model.Issue;

public class IssueValidator implements Validator {

	private static final Pattern VALID_ADMIN_NUMBER_REGEX = 
		    Pattern.compile("[A][0-9]{4}");
	private static final Pattern VALID_FACILITY_NUMBER_REGEX = 
		    Pattern.compile("[F][0-9]{3}");
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Issue.class.equals(clazz);
	}

	@Override
	public void validate (Object target, Errors errors) {
		
		Issue i = (Issue) target;
		
		if (i.getIssueNumber() == null) errors.rejectValue("issueNumber", "issueNumber.null", "Issue number cannot be null");
	    if (i.getAdminNumber() == null) errors.rejectValue("adminNumber", "adminNumber.null", "Admin number cannot be null");
        if (i.getFacilityNumber() == null) errors.rejectValue("facilityNumber", "facilityNumber.null", "Facility number cannot be null");
        if (i.getReportDateTime() == null) errors.rejectValue("reportDateTime", "reportDateTime.null", "Report date time cannot be null");
        if (i.getIssueDescription() == null) errors.rejectValue("issueDescription", "issueDescription.null", "Issue description cannot be null");
        if (i.getIssueStatus() == null) errors.rejectValue("issueStatus", "issueStatus.null", "Issue status cannot be null");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "issueNumber", "issueNumber.empty", "Issue number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adminNumber", "adminNumber.empty", "Admin number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "facilityNumber", "facilityNumber.empty", "Facility Number cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reportDateTime", "reportDateTime.empty", "Report date time cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "issueDescription", "issueDescription.empty", "Issue description cannot be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "issueStatus", "issueStatus.empty", "Issue status cannot be empty");
		
		Matcher adminNumberMatcher = VALID_ADMIN_NUMBER_REGEX.matcher(i.getAdminNumber());
		if (!adminNumberMatcher.find()) {
			errors.rejectValue("adminNumber", "adminNumber.invalid", "Invalid admin number format");
		}
		Matcher facilityNumberMatcher = VALID_FACILITY_NUMBER_REGEX.matcher(i.getFacilityNumber());
		if (!facilityNumberMatcher.find()) {
			errors.rejectValue("facilityNumber", "facilityNumber.invalid", "Invalid facility name format");
		}
		
		if (i.getIssueStatus() != "Open" && i.getIssueStatus() != "Ongoing" && i.getIssueStatus() != "Cancelled" && i.getIssueStatus() != "Closed") {
			errors.rejectValue("issueStatus", "issueStatus.invalid", "Not a valid issue status");
		}

	}

}
