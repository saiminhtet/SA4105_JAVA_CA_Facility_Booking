package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.FacilityRepository;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Resource
	private FacilityRepository fRepo;
	
	@Override
	public Facility setNewFacNum(Facility f) throws FacilityNotFound {
		if (f == null) throw new FacilityNotFound("Facility object provided cannot be null");
		
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
	public Facility addFacility(Facility f) throws FacilityNotFound {
		if (f == null) throw new FacilityNotFound("Facility object provided cannot be null");
		return fRepo.saveAndFlush(f);
	}
	
	@Override
	@Transactional
	public Facility getFacility(String fNum) throws FacilityNotFound {
		Facility f = fRepo.getFacilityByFacilityNumber(fNum);
		if (f == null) throw new FacilityNotFound("Facility number provided is invalid");
		else return f;
	}
	
	@Override
	@Transactional
	public Facility updateFacility(Facility f) throws FacilityNotFound {
		if (f == null) throw new FacilityNotFound("Facility object provided cannot be null");
		return fRepo.saveAndFlush(f);
	}
	
	@Override
	@Transactional
	public Void removeFacility(String fNum) throws FacilityNotFound {
		if (getFacility(fNum) == null) throw new FacilityNotFound("Facility number provided is invalid");
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
	public ArrayList<Facility> getFacilityListByNumber(String fNum) throws FacilityNotFound {
		if (getFacility(fNum) == null) throw new FacilityNotFound("Facility number provided is invalid");
		
		// Retrieve Existing Facility Object
		Facility f = getFacility(fNum);
		
		// Retrieve All Facility Objects of Same Type
		return fRepo.getFacilityListByType(f.getFacilityType());
	}
	
	@Override
	public ArrayList<Facility> getFacilityListByName(String fName) {
		return fRepo.getFacilityListByName(fName);
	}
	
	@Override
	public Facility getFacilityByName(String fName) {
		return fRepo.getFacilityByName(fName);
	}
	
	@Override
	public Facility getFacility(Booking b) throws BookingNotFound, FacilityNotFound {
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		Facility f = getFacility(b.getFacilityNumber());
		if (f == null) throw new FacilityNotFound("Unable to find Facility object based on FacilityNumber in Booking object");
		return f;
	}
}
