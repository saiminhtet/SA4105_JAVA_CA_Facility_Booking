package com.lsc.mvc.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="issue")
public class Issue {
	
	// Entity Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Integer issueId;
	
	@NotBlank
	@Column(name = "issue_number")
	protected String issueNumber;
	
	@NotBlank
	@Column(name = "admin_number")
	protected String adminNumber;
	
	@NotBlank
	@Column(name = "facility_number")
	protected String facilityNumber;

	@Column(name = "report_datetime")
	protected LocalDateTime reportDateTime;
	
	@NotBlank
	@Column(name = "issue_description")
	protected String issueDescription;
	
	@NotBlank
	@Column(name = "issue_status")
	protected String issueStatus;
		
	// Getters and Setters
	public Integer getIssueId() { return issueId; }
	public void setIssueId(Integer issueId) { this.issueId = issueId; }
	
	public String getIssueNumber() { return issueNumber; }
	public void setIssueNumber(String issueNumber) { this.issueNumber = issueNumber; }
	
	public String getAdminNumber() { return adminNumber; }
	public void setAdminNumber(String adminNumber) { this.adminNumber = adminNumber; }
	
	public String getFacilityNumber() { return facilityNumber; }
	public void setFacilityNumber(String facilityNumber) { this.facilityNumber = facilityNumber; }
	
	public LocalDateTime getReportDateTime() { return reportDateTime; }
	public void setReportDateTime(LocalDateTime reportDateTime) { this.reportDateTime = reportDateTime; }
	
	public String getIssueDescription() { return issueDescription; }
	public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }
	
	public String getIssueStatus() { return issueStatus; }
	public void setIssueStatus(String issueStatus) { this.issueStatus = issueStatus; }
	
	// Constructors
	public Issue() { }
	public Issue(@NotBlank String adminNumber, @NotBlank String facilityNumber, 
			LocalDateTime reportDateTime, @NotBlank String issueDescription,
			@NotBlank String issueStatus) {
		super();
		this.adminNumber = adminNumber;
		this.facilityNumber = facilityNumber;
		this.reportDateTime = LocalDateTime.now();
		this.issueDescription = issueDescription;
		this.issueStatus = "Open";
	}
	
	// Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminNumber == null) ? 0 : adminNumber.hashCode());
		result = prime * result + ((facilityNumber == null) ? 0 : facilityNumber.hashCode());
		result = prime * result + ((issueDescription == null) ? 0 : issueDescription.hashCode());
		result = prime * result + ((issueId == null) ? 0 : issueId.hashCode());
		result = prime * result + ((issueNumber == null) ? 0 : issueNumber.hashCode());
		result = prime * result + ((issueStatus == null) ? 0 : issueStatus.hashCode());
		result = prime * result + ((reportDateTime == null) ? 0 : reportDateTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (issueId == null) {
			if (other.issueId != null)
				return false;
		} else if (!issueId.equals(other.issueId))
			return false;
		if (issueNumber == null) {
			if (other.issueNumber != null)
				return false;
		} else if (!issueNumber.equals(other.issueNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Issue [issueId=" + issueId + ", "
				+ "issueNumber=" + issueNumber + ", "
				+ "adminNumber=" + adminNumber + ", "
				+ "facilityNumber=" + facilityNumber + ", "
				+ "reportDateTime=" + reportDateTime + ", "
				+ "issueDescription=" + issueDescription + ", "
				+ "issueStatus=" + issueStatus + "]";
	}
	
//	public static ArrayList<Issue> getIssueListByFacilityNumber(String facNum) {
//		// Dummy code: 4 Issue objects hard-coded and added to 1 ArrayList<Issue>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Issue> iList = getIssueListByFacilityNumber(facNum);
//		
//		// Initialize ArrayList<Issue>
//		ArrayList<Issue> iList = new ArrayList<Issue>();
//		
//		// Validate facNum
//
//			// Gets Issue objects based on facNum
//			// and add Issue objects to ArrayList<Issue>
//		
//		
//		Issue i1 = new Issue();
//		i1.issueNumber = "I0001";
//		i1.adminNumber = "A02";
//		i1.facilityNumber = "F03";
//		i1.reportDateTime = LocalDateTime.of(2018, 5, 8, 14, 00);
//		i1.issueDescription = "This is a sample description that is relatively long.";
//		i1.issueStatus = "Closed";
//		iList.add(i1);
//
//		Issue i2 = new Issue();
//		i2.issueNumber = "I0002";
//		i2.adminNumber = "A03";
//		i2.facilityNumber = "F01";
//		i2.reportDateTime = LocalDateTime.of(2018, 5, 10, 14, 00);
//		i2.issueDescription = "This is a sample description that is relatively long.";
//		i2.issueStatus = "Cancelled";
//		iList.add(i2);
//		
//		Issue i3 = new Issue();
//		i3.issueNumber = "I0003";
//		i3.adminNumber = "A02";
//		i3.facilityNumber = "F02";
//		i3.reportDateTime = LocalDateTime.of(2018, 6, 1, 14, 00);
//		i3.issueDescription = "This is a sample description that is relatively long.";
//		i3.issueStatus = "Ongoing";
//		iList.add(i3);
//		
//		Issue i4 = new Issue();
//		i4.issueNumber = "I0004";
//		i4.adminNumber = "A05";
//		i4.facilityNumber = "F03";
//		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		i4.issueDescription = "This is a sample description that is relatively long.";
//		i4.issueStatus = "Open";
//		iList.add(i4);
//		
//		return iList;
//	}
//    public static ArrayList<Issue> getIssueListByIssueNumber(String issueNum) {
//    	// Dummy code: 1 Issue object hard-coded and added to 1 ArrayList<Issue>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Issue> iList = getIssueListByIssueNumber(issueNum);
//		
//		// Initialize ArrayList<Issue>
//		ArrayList<Issue> iList = new ArrayList<Issue>();
//		
//		// Validate issueNum
//
//			// Gets Issue object based on issueNum
//			// and add Issue object to ArrayList<Issue>
//		
//		Issue i4 = new Issue();
//		i4.issueNumber = "I0004";
//		i4.adminNumber = "A05";
//		i4.facilityNumber = "F03";
//		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		i4.issueDescription = "This is a sample description that is relatively long.";
//		i4.issueStatus = "Open";
//		iList.add(i4);
//		
//		return iList;
//	}
//	public static Issue getIssue(String issueNum) {
//		// Dummy code: 1 Issue object hard-coded and returned as Issue
//		
//		// Example Implementation: 
//		// Issue i = getIssue(issueNum);
//		
//		// Validate issueNum
//
//			// Gets Issue object based on issueNum
//		
//		Issue i4 = new Issue();
//		i4.issueNumber = "I0004";
//		i4.adminNumber = "A05";
//		i4.facilityNumber = "F03";
//		i4.reportDateTime = LocalDateTime.of(2018, 6, 6, 14, 00);
//		i4.issueDescription = "This is a sample description that is relatively long.";
//		i4.issueStatus = "Open";
//		return i4;
//	}
//	public static String updateIssue(String issueNum, String desc, String sts) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(updateIssue(issueNum, desc, sts) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate issueNum
//		
//		// Validate sts
//		
//		return err;
//	}
//	public static String addIssue(String userNum, String facNum, String desc) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(addIssue(userNum, facNum, desc) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate userNum
//		
//		// Validate facNum
//		
//		return err;
//	}
//	public static String removeIssue(String issueNum) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(removeIssue(issueNum) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Validate issueNum
//		
//		// Checks that issueNum exists in database
//		
//		// Find Issue object
//		
//		// Remove Issue object
//		
//		return err;
//	}
}
