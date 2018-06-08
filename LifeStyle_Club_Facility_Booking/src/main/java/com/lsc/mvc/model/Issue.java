package com.lsc.mvc.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Issue {
	
	// Class Variables
	
	// --------------------------------------------------
	
		protected String issueNumber;
		protected String adminNumber;
		protected String facilityNumber;
		protected LocalDateTime reportDateTime;
		protected String issueDescription;
		protected String issueStatus;
		
		public String getIssueNumber() { return issueNumber; }
		public String getAdminNumber() { return adminNumber; }
		public String getFacilityNumber() { return facilityNumber; }
		public LocalDateTime getReportDateTime() { return reportDateTime; }
		public String getIssueDescription() { return issueDescription; }
		public String getIssueStatus() { return issueStatus; }
		
	// --------------------------------------------------
	
	public static ArrayList<Issue> getIssueListByFacilityNumber(String facNum) {
		// Dummy code: 4 Issue objects hard-coded and added to 1 ArrayList<Issue>, 
		// then returned
		
		// Example Implementation: 
		// ArrayList<Issue> iList = getIssueListByFacilityNumber(facNum);
		
		// Initialize ArrayList<Issue>
		ArrayList<Issue> iList = new ArrayList<Issue>();
		
		// Validate facNum

			// Gets Issue objects based on facNum
			// and add Issue objects to ArrayList<Issue>
		
		
		Issue i1 = new Issue();
		i1.issueNumber = "I0001";
		i1.adminNumber = "A02";
		i1.facilityNumber = "F03";
		i1.reportDateTime = LocalDateTime.of(2018, 5, 8, 14, 00);
		i1.issueDescription = "This is a sample description that is relatively long.";
		i1.issueStatus = "Closed";
		iList.add(i1);

		Issue i2 = new Issue();
		i2.issueNumber = "I0002";
		i2.adminNumber = "A03";
		i2.facilityNumber = "F01";
		i2.reportDateTime = LocalDateTime.of(2018, 5, 10, 14, 00);
		i2.issueDescription = "This is a sample description that is relatively long.";
		i2.issueStatus = "Cancelled";
		iList.add(i2);
		
		Issue i3 = new Issue();
		i3.issueNumber = "I0003";
		i3.adminNumber = "A02";
		i3.facilityNumber = "F02";
		i3.reportDateTime = LocalDateTime.of(2018, 6, 1, 14, 00);
		i3.issueDescription = "This is a sample description that is relatively long.";
		i3.issueStatus = "Ongoing";
		iList.add(i3);
		
		Issue i4 = new Issue();
		i4.issueNumber = "I0004";
		i4.adminNumber = "A05";
		i4.facilityNumber = "F03";
		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
		i4.issueDescription = "This is a sample description that is relatively long.";
		i4.issueStatus = "Open";
		iList.add(i4);
		
		return iList;
	}
    public static ArrayList<Issue> getIssueListByIssueNumber(String issueNum) {
    	// Dummy code: 1 Issue object hard-coded and added to 1 ArrayList<Issue>, 
		// then returned
		
		// Example Implementation: 
		// ArrayList<Issue> iList = getIssueListByIssueNumber(issueNum);
		
		// Initialize ArrayList<Issue>
		ArrayList<Issue> iList = new ArrayList<Issue>();
		
		// Validate issueNum

			// Gets Issue object based on issueNum
			// and add Issue object to ArrayList<Issue>
		
		Issue i4 = new Issue();
		i4.issueNumber = "I0004";
		i4.adminNumber = "A05";
		i4.facilityNumber = "F03";
		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
		i4.issueDescription = "This is a sample description that is relatively long.";
		i4.issueStatus = "Open";
		iList.add(i4);
		
		return iList;
	}
	public static Issue getIssue(String issueNum) {
		// Dummy code: 1 Issue object hard-coded and returned as Issue
		
		// Example Implementation: 
		// Issue i = getIssue(issueNum);
		
		// Validate issueNum

			// Gets Issue object based on issueNum
		
		Issue i4 = new Issue();
		i4.issueNumber = "I0004";
		i4.adminNumber = "A05";
		i4.facilityNumber = "F03";
		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
		i4.issueDescription = "This is a sample description that is relatively long.";
		i4.issueStatus = "Open";
		return i4;
	}
	public static String updateIssue(String issueNum, String desc, String sts) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(updateIssue(issueNum, desc, sts) != "") { ... } else { ... }
		
		String err = "";
		
		// Checks that all required fields provided
		
		// Validate issueNum
		
		// Validate sts
		
		return err;
	}
	public static String addIssue(String userNum, String facNum, String desc) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(addIssue(userNum, facNum, desc) != "") { ... } else { ... }
		
		String err = "";
		
		// Checks that all required fields provided
		
		// Validate userNum
		
		// Validate facNum
		
		return err;
	}
	public static String removeIssue(String issueNum) {
		// Dummy code: return success
		// If there is any error at any point, the error will be appended to err
		
		// Example Implementation: 
		// if(removeIssue(issueNum) != "") { ... } else { ... }
		
		String err = "";
		
		// Validate issueNum
		
		// Checks that issueNum exists in database
		
		// Find Issue object
		
		// Remove Issue object
		
		return err;
	}
}
