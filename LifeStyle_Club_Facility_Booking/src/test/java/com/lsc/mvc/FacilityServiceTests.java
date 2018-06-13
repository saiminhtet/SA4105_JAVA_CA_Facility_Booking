package com.lsc.mvc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.FacilityRepository;
import com.lsc.mvc.service.FacilityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacilityServiceTests {

	@Autowired
	private FacilityService fService;
	@Autowired
	private FacilityRepository fRepo;
	
//	@Test
//	public void whenGetFacility_thenReturnFacility() {
//		Facility f;
//		try {
//			f = fService.getFacility("F010");
//			outputStringToConsole(f.toString());
//		} catch (FacilityNotFound e) {
//			outputStringToConsole(e.getMessage());
//		}
//		try {
//			f = fService.getFacility("F0102");
//			outputStringToConsole(f.toString());
//		} catch (FacilityNotFound e) {
//			outputStringToConsole(e.getMessage());
//		}
//	}
	
//	@Test
//	public void whenAddFacility_thenReturnFacility() {
//		Facility f = new Facility("Badminton Court", 
//				"Badminton Court 5", "This is a long description", 4);
//		fService.setNewFacNum(f);
//		
//		outputStringToConsole(f.toString());
//		
//		// Adding To Database
//		fService.addFacility(f);
//		
//		for (Facility fac:fRepo.findAll()) {
//			System.out.println(fac.toString());
//		}
//	}
	
//	@Test
//	public void whenUpdateFacility_thenReturnFacility() {
//		// Retrieving Existing Facility Object
//		Facility f = fService.getFacility("F026");
//		
//		// Modifying Details
//		f.setFacilityType("Basketball Court");
//		f.setFacilityName("Basketball Court 3");
//		f.setFacilityDescription("Another long description");
//		f.setCapacity(10);
//		
//		outputStringToConsole(f.toString());
//		
//		// Updating To Database
//		fService.updateFacility(f);
//		
//		for (Facility fac:fRepo.findAll()) {
//			System.out.println(fac.toString());
//		}
//	}
	
//	@Test
//	public void whenRemoveFacility_thenReturnVoid() {
//		// Removing From Database
//		fService.removeFacility("F026");
//		
//		for (Facility fac:fRepo.findAll()) {
//			System.out.println(fac.toString());
//		}
//	}
	
//	@Test
//	public void whenGetFacilityTypes_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<String> fTypeList = fService.getFacilityTypes();
//		
//		outputStringToConsole(String.valueOf(fTypeList.size()));
//		
//		for (String t:fTypeList) {
//			System.out.println(t.toString());
//		}
//	}
	
//	@Test
//	public void whenGetFacilityListByType_thenReturnArrayList() {
//		outputStringToConsole("Getting Courts");
//		// Retrieving ArrayList
//		ArrayList<Facility> fList1 = fService.getFacilityListByType("Court");
//		
//		outputStringToConsole(String.valueOf(fList1.size()));
//		
//		for (Facility f:fList1) {
//			System.out.println(f.toString());
//		}
//		outputStringToConsole("Getting Rooms");
//		ArrayList<Facility> fList2 = fService.getFacilityListByType("Room");
//		
//		outputStringToConsole(String.valueOf(fList2.size()));
//		
//		for (Facility f:fList2) {
//			System.out.println(f.toString());
//		}
//	}
	
//	@Test
//	public void whenGetFacilityListByNumber_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<Facility> fList1 = fService.getFacilityListByNumber("F020");
//		
//		outputStringToConsole(String.valueOf(fList1.size()));
//		
//		for (Facility f:fList1) {
//			System.out.println(f.toString());
//		}
//	}
	
//	@Test
//	public void whenGetFacilityListByName_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<Facility> fList1 = fService.getFacilityListByName("roOm");
//		
//		outputStringToConsole(String.valueOf(fList1.size()));
//		
//		for (Facility f:fList1) {
//			System.out.println(f.toString());
//		}
//	}
	
//	@Test
//	public void whenGetFacilityByName_thenReturnFacility() {
//		// Retrieving ArrayList
//		Facility f = fService.getFacilityByName("Tennis Court 2");
//		
//		outputStringToConsole(f.toString());
//	}
	
//	@Test
//	public void whenGetFacilityList_thenReturnList() {
//		List<Facility> fList = fService.getFacilityList();
//		for (Facility f:fList) {
//			System.out.println(f.toString());
//		}
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
