package com.lsc.mvc.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Facility")
public class Facility {
	
	// Entity Mapping
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "facility_id")
	private Integer facilityId;
	
	@NotBlank
	@Column(name = "facility_number")
	protected String facilityNumber;
	
	@NotBlank
	@Column(name = "facility_type")
	protected String facilityType;
	
	@NotBlank
	@Column(name = "facility_name")
	protected String facilityName;
	
	@NotBlank
	@Column(name = "facility_description")
	protected String facilityDescription;
	
	@Column(name = "capacity")
	protected Integer capacity;
		
	// Getters and Setters
	public Integer getFacilityId() { return facilityId; }
	public void setFacilityId(Integer facilityId) { this.facilityId = facilityId; }
	
	public void setFacilityNumber(String facilityNumber) { this.facilityNumber = facilityNumber; }
	public String getFacilityNumber() { return facilityNumber; }
	
	public void setFacilityType(String facilityType) { this.facilityType = facilityType; }
	public String getFacilityType() { return facilityType; }
	
	public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
	public String getFacilityName() { return facilityName; }
	
	public void setFacilityDescription(String facilityDescription) { this.facilityDescription = facilityDescription; }
	public String getFacilityDescription() { return facilityDescription; }
	
	public void setCapacity(Integer capacity) { this.capacity = capacity; }
	public Integer getCapacity() { return capacity; }
	
	// Constructors
	public Facility() { }
	public Facility(@NotBlank String facilityType, 
			@NotBlank String facilityName, 
			@NotBlank String facilityDescription, 
			@NotBlank Integer capacity) {
		super();
		this.facilityType = facilityType;
		this.facilityName = facilityName;
		this.facilityDescription = facilityDescription;
		this.capacity = capacity;
	}
	
	// Methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((capacity == null) ? 0 : capacity.hashCode());
		result = prime * result + ((facilityDescription == null) ? 0 : facilityDescription.hashCode());
		result = prime * result + ((facilityId == null) ? 0 : facilityId.hashCode());
		result = prime * result + ((facilityName == null) ? 0 : facilityName.hashCode());
		result = prime * result + ((facilityNumber == null) ? 0 : facilityNumber.hashCode());
		result = prime * result + ((facilityType == null) ? 0 : facilityType.hashCode());
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
		Facility other = (Facility) obj;
		if (facilityId == null) {
			if (other.facilityId != null)
				return false;
		} else if (!facilityId.equals(other.facilityId))
			return false;
		if (facilityNumber == null) {
			if (other.facilityNumber != null)
				return false;
		} else if (!facilityNumber.equals(other.facilityNumber))
			return false;
		if (facilityName == null) {
			if (other.facilityName != null)
				return false;
		} else if (!facilityName.equals(other.facilityName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Facility [facilityId=" + facilityId + ", "
				+ "facilityNumber=" + facilityNumber + ", "
				+ "facilityType=" + facilityType + ", "
				+ "facilityName=" + facilityName + ", "
				+ "facilityDescription=" + facilityDescription + ", "
				+ "capacity=" + capacity + "]";
	}
}
