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
	
	
//	public static String validateFacilityNumber(String facNum) {
//		// Dummy Code: return no error
//		// If there is any error at any check, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(validateFacilityNumber(facNum)!= "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that no blanks
//		
//		// Checks that facNum matches pattern F###
//		
//		return err;
//	}
//	
//	public static ArrayList<String> getFacilityTypes() {
//		// Dummy code: returns hard-coded arraylist of facility types
//		
//		// Example Implementation:
//		// ArrayList<String> fTypeList = getFacilityTypes();
//		
//		// Initialize ArrayList<String>
//		ArrayList<String> fTypeList = new ArrayList<String>();
//		
//		// Gets all unique facilityTypes from database
//		// and adds to arraylist to return
//		
//		fTypeList.add("Conference Room");
//		fTypeList.add("Meeting Room");
//		fTypeList.add("Karaoke Room");
//		fTypeList.add("Function Room");
//		fTypeList.add("Badminton Court");
//		fTypeList.add("Squash Court");
//		fTypeList.add("Tennis Court");
//		fTypeList.add("Basketball Court");
//		
//		return fTypeList;
//	}
//	public static ArrayList<Facility> getFacilityListByType(String facType) {
//		// Dummy code: 4 Facility objects hard-coded and added to 1 ArrayList<Facility>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Facility> fList = getFacilityListByType(facType);
//		
//		// Initialize ArrayList<Facility>
//		ArrayList<Facility> fList = new ArrayList<Facility>();
//		
//		// Validate facType
//
//			// Gets Facility objects based on facType
//			// and add Facility objects to ArrayList<Facility>
//		
//		Facility f1 = new Facility();
//		f1.facilityNumber = "F01";
//		f1.facilityType = "Conference Room";
//		f1.facilityName = "Platinum Room";
//		f1.facilityDescription = "This is a sample description that is relatively long.";
//		f1.capacity = 40;
//		fList.add(f1);
//		
//		Facility f2 = new Facility();
//		f2.facilityNumber = "F02";
//		f2.facilityType = "Conference Room";
//		f2.facilityName = "Sapphire Room";
//		f2.facilityDescription = "This is a sample description that is relatively long.";
//		f2.capacity = 20;
//		fList.add(f2);
//		
//		Facility f3 = new Facility();
//		f3.facilityNumber = "F03";
//		f3.facilityType = "Tennis Court";
//		f3.facilityName = "Tennis Court 1";
//		f3.facilityDescription = "This is a sample description that is relatively long.";
//		f3.capacity = 4;
//		fList.add(f3);
//		
//		Facility f4 = new Facility();
//		f4.facilityNumber = "F04";
//		f4.facilityType = "Badminton Court";
//		f4.facilityName = "Badminton Court 1";
//		f4.facilityDescription = "This is a sample description that is relatively long.";
//		f4.capacity = 4;
//		fList.add(f4);
//		
//		return fList;
//	}
//	public static ArrayList<Facility> getFacilityListByNumber(String facNum) {
//		// Dummy code: 1 Facility object hard-coded and added to 1 ArrayList<Facility>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Facility> fList = getFacilityListByNumber(facNum);
//		
//		// Initialize ArrayList<Facility>
//		ArrayList<Facility> fList = new ArrayList<Facility>();
//		
//		// Validate facType
//
//			// Gets Facility object based on facNum
//			// and add Facility object to ArrayList<Facility>
//		
//		Facility f4 = new Facility();
//		f4.facilityNumber = "F04";
//		f4.facilityType = "Badminton Court";
//		f4.facilityName = "Badminton Court 1";
//		f4.facilityDescription = "This is a sample description that is relatively long.";
//		f4.capacity = 4;
//		fList.add(f4);
//		
//		return fList;
//	}
//	public static ArrayList<Facility> getFacilityListByName(String facName){
//		// Dummy code: 4 Facility objects hard-coded and added to 1 ArrayList<Facility>, 
//		// then returned
//		
//		// Example Implementation: 
//		// ArrayList<Facility> fList = getFacilityListByName(facName);
//		
//		// Initialize ArrayList<Facility>
//		ArrayList<Facility> fList = new ArrayList<Facility>();
//		
//		// Validate facType
//
//			// Gets Facility objects based on facName
//			// and add Facility objects to ArrayList<Facility>
//		
//		Facility f1 = new Facility();
//		f1.facilityNumber = "F01";
//		f1.facilityType = "Conference Room";
//		f1.facilityName = "Platinum Room";
//		f1.facilityDescription = "This is a sample description that is relatively long.";
//		f1.capacity = 40;
//		fList.add(f1);
//		
//		Facility f2 = new Facility();
//		f2.facilityNumber = "F02";
//		f2.facilityType = "Conference Room";
//		f2.facilityName = "Sapphire Room";
//		f2.facilityDescription = "This is a sample description that is relatively long.";
//		f2.capacity = 20;
//		fList.add(f2);
//		
//		Facility f3 = new Facility();
//		f3.facilityNumber = "F05";
//		f3.facilityType = "Meeting Room";
//		f3.facilityName = "Opal Room";
//		f3.facilityDescription = "This is a sample description that is relatively long.";
//		f3.capacity = 12;
//		fList.add(f3);
//		
//		Facility f4 = new Facility();
//		f4.facilityNumber = "F04";
//		f4.facilityType = "Function Room";
//		f4.facilityName = "Diamond Room";
//		f4.facilityDescription = "This is a sample description that is relatively long.";
//		f4.capacity = 30;
//		fList.add(f4);
//		
//		return fList;
//	}
//	public static Facility getFacility(String facNum) {
//		// Dummy code: 1 Facility object hard-coded and returned
//		
//		// Example Implementation: 
//		// Facility f = getFacility(facNum);
//		
//		// Check that no blanks
//		
//		// Validate facNum
//		
//		// Get Facility object based on facNum
//		
//		Facility f = new Facility();
//		f.facilityNumber = "F04";
//		f.facilityType = "Badminton Court";
//		f.facilityName = "Badminton Court 1";
//		f.facilityDescription = "This is a sample description that is relatively long.";
//		f.capacity = 4;
//		
//		return f;
//	}
//	public static String updateFacility
//		(String facNum, String facType, String facName,
//	     String facDesc, Integer cap) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(updateFacility
//		//		(facNum, facType, facName,
//		//	    facDesc, cap) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate facNum
//		
//		// Validate facType
//		
//		// Validate cap
//		
//		return err;
//		
//	}
//	public static String addFacility
//	    (String facType, String facName,
//	     String facDesc, Integer cap) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(addFacility
//		//		(facType, facName,
//		//	    facDesc, cap) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Checks that all required fields provided
//		
//		// Validate facType
//		
//		// Validate cap
//		
//		return err;
//		
//	}
//	public static String removeFacility(String facNum) {
//		// Dummy code: return success
//		// If there is any error at any point, the error will be appended to err
//		
//		// Example Implementation: 
//		// if(removeFacility(facNum) != "") { ... } else { ... }
//		
//		String err = "";
//		
//		// Validate facNum
//		
//		// Checks that facNum exists in database
//		
//		// Find Facility object
//		
//		// Remove Facility object
//		
//		return err;
//	}
}
