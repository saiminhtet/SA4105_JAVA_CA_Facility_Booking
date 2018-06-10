package com.lsc.mvc;

import java.text.DecimalFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserModelAndRepoTests {
	
	@Autowired
	private UserRepository uRepo;
	
//	@Test
//	public void whenGetUserByUserNumber_thenReturnUser() {
//		User u = uRepo.getUserByUserNumber("M0055");
//		outputStringToConsole(u.toString());
//	}
	
//	@Test
//	public void whenGetUserIdMax_thenReturnInteger() {
//		Integer maxId = uRepo.getUserIdMax();
//		outputStringToConsole(maxId.toString());
//	}
	
//	@Test
//	public void whenAddUserWithMiddleName_thenReturnString() {
//		// Making New User Object
//		User u = new User("Mr", "Buenos", "Amigos", "Lazada", 
//				"Aa123!@#", "ba@ba.com", "6512345678");
//		
//		// Getting Current Max userId
//		Integer newId = uRepo.getUserIdMax() + 1;
//		
//		// Assigning New userNumber
//		DecimalFormat fmt = new DecimalFormat("0000");
//		u.setUserNumber("M" + fmt.format(newId));
//		
//		outputStringToConsole(u.toString());
//		
//		// Adding To Database
//		uRepo.saveAndFlush(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenAddUserWithMiddleName_thenReturnString() {
//		// Making New User Object
//		User u = new User("Mr", "Buenos", "Amigos", null, 
//				"Aa123!@#", "ba@ba.com", "6512345678");
//		
//		// Getting Current Max userId
//		Integer newId = uRepo.getUserIdMax() + 1;
//		
//		// Assigning New userNumber
//		DecimalFormat fmt = new DecimalFormat("0000");
//		u.setUserNumber("M" + fmt.format(newId));
//		
//		outputStringToConsole(u.toString());
//		
//		// Adding To Database
//		uRepo.saveAndFlush(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenUpdateUser_thenReturnString() {
//		// Retrieving Existing User Object
//		User u = uRepo.getUserByUserNumber("M0054");
//		
//		// Modifying Details
//		u.setTitle("Dr");
//		u.setFirstName("McDonalds");
//		u.setLastName("Ronald");
//		u.setMiddleName("");
//		u.setPassword("qwe123");
//		u.setEmailAddress("ronald@yahoo.com");
//		u.setPhoneNumber("6599999999");
//		
//		outputStringToConsole(u.toString());
//		
//		// Updating To Database
//		uRepo.saveAndFlush(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenRemoveUser_thenReturnString() {
//		// Retrieving Existing User Object
//		User u = uRepo.getUserByUserNumber("M0054");
//		
//		outputStringToConsole(u.toString());
//		
//		// Removing From Database
//		uRepo.delete(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
