package com.lsc.mvc.service;

import java.util.ArrayList;

import org.springframework.data.repository.query.Param;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.model.Facility;

public interface FacilityService {
	
	Facility setNewFacNum(Facility f) throws FacilityNotFound;
	
	Facility addFacility(Facility f) throws FacilityNotFound;
	
	Facility getFacility(String fNum) throws FacilityNotFound;

	Facility updateFacility(Facility f) throws FacilityNotFound;

	Void removeFacility(String fNum) throws FacilityNotFound;
	
	ArrayList<String> getFacilityTypes();
	
	ArrayList<Facility> getFacilityListByType(String fType);
	
	ArrayList<Facility> getFacilityListByNumber(String fNum) throws FacilityNotFound;
	
	ArrayList<Facility> getFacilityListByName(String fName);
	
	Facility getFacilityByName(String fName);
}
