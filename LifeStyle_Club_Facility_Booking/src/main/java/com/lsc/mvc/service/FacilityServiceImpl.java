package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Resource
	private FacilityRepository fRepo;
	
	@Override
	public Facility setNewFacNum(Facility f) {
		// Getting Current Max userId
		Integer newId = fRepo.getFacIdMax() + 1;
		
		// Assigning New facNum
		DecimalFormat fmt = new DecimalFormat("000");
		String prefix = "F";
		f.setFacilityNumber(prefix + fmt.format(newId));
		return f;
	}
	
	@Override
	@Transactional
	public Facility addFacility(Facility f) {
		return fRepo.saveAndFlush(f);
	}
	
	@Override
	@Transactional
	public Facility getFacility(String fNum) {
		return fRepo.getFacilityByFacilityNumber(fNum);
	}
	
	@Override
	@Transactional
	public Facility updateFacility(Facility f) {
		return fRepo.saveAndFlush(f);
	}
	
	@Override
	@Transactional
	public Void removeFacility(String fNum) {
		fRepo.delete(getFacility(fNum));
		return null;
	}
	
	@Override
	public ArrayList<String> getFacilityTypes() {
		return fRepo.getFacilityTypes();
	}
	
	@Override
	public ArrayList<Facility> getFacilityListByType(String fType){
		return fRepo.getFacilityListByType(fType);
	}
	
	@Override
	public ArrayList<Facility> getFacilityListByNumber(String fNum) {
		// Retrieve Existing Facility Object
		Facility f = getFacility(fNum);
		
		// Create New ArrayList and Add Facility Object
		ArrayList<Facility> fList = new ArrayList<Facility>();
		fList.add(f);
		return fList;
	}
	
	@Override
	public ArrayList<Facility> getFacilityListByName(String fName) {
		return fRepo.getFacilityListByName(fName);
	}
}
