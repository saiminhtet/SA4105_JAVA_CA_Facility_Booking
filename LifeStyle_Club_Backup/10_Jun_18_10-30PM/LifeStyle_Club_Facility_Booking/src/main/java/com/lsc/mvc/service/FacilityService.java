package com.lsc.mvc.service;

import java.util.ArrayList;

import org.springframework.data.repository.query.Param;

import com.lsc.mvc.model.Facility;

public interface FacilityService {
	
	Facility setNewFacNum(Facility f);
	
	Facility addFacility(Facility f);
	
	Facility getFacility(String fNum);

	Facility updateFacility(Facility f);

	Void removeFacility(String fNum);
	
	ArrayList<String> getFacilityTypes();
	
	ArrayList<Facility> getFacilityListByType(String fType);
	
	ArrayList<Facility> getFacilityListByNumber(String fNum);
	
	ArrayList<Facility> getFacilityListByName(String fName);
}
