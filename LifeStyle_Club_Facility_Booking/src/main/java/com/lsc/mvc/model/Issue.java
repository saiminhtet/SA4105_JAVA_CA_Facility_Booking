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
}
