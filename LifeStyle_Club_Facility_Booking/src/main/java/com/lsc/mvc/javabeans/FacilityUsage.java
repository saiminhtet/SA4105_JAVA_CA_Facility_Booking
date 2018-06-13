package com.lsc.mvc.javabeans;

import java.text.DecimalFormat;

public class FacilityUsage {
	
	// Attributes
	private String facilityNumber;
	private String facilityType;
	private String facilityName;
	private String facilityUsage;
	
	// Getters and Setters
	public String getFacilityNumber() { return facilityNumber; }
	public void setFacilityNumber(String facilityNumber) { this.facilityNumber = facilityNumber; }
	public String getFacilityType() { return facilityType; }
	public void setFacilityType(String facilityType) { this.facilityType = facilityType; }
	public String getFacilityName() { return facilityName; }
	public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
	public String getFacilityUsage() { return facilityUsage; }
	public void setFacilityUsage(String facilityUsage) { this.facilityUsage = facilityUsage; }
	
	// Constructors
	public FacilityUsage() { }
	
	public FacilityUsage(String facilityNumber, String facilityType, String facilityName, String facilityUsage) {
		super();
		this.facilityNumber = facilityNumber;
		this.facilityType = facilityType;
		this.facilityName = facilityName;
		this.facilityUsage = facilityUsage;
	}
	
	// Methods
	@Override
	public String toString() {
		return "FacilityUsage [facilityNumber=" + facilityNumber + ", facilityType=" + facilityType + ", facilityName="
				+ facilityName + ", facilityUsage=" + facilityUsage + "]";
	}
}
