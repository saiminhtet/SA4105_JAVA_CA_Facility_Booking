package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.Facility;
import com.lsc.mvc.repository.FacilityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacilityModelAndRepoTests {

	@Autowired
	private FacilityRepository fRepo;
	
//	@Test
//	public void whenGetFacilityByFacilityNumber_thenReturnFacility() {
//		Facility f = fRepo.getFacilityByFacilityNumber("F010");
//		outputStringToConsole(f.toString());
//	}
	
//	@Test
//	public void whenGetFacIdMax_thenReturnInteger() {
//		Integer maxId = fRepo.getFacIdMax();
//		outputStringToConsole(maxId.toString());
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
