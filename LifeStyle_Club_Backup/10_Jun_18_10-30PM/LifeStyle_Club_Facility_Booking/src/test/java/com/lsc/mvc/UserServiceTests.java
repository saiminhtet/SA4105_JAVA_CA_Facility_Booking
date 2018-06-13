package com.lsc.mvc;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;
import com.lsc.mvc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
	
	@Autowired
	private UserService uService;
	@Autowired
	private UserRepository uRepo;
	
//	@Test
//	public void whenGetUser_thenReturnUser() {
//		User u = uService.getUser("M0055");
//		outputStringToConsole(u.toString());
//	}
	
//	@Test
//	public void whenAddUser_thenReturnUser() {
//		User u = new User("Mr", "Buenos", "Amigos", "Sayonara", 
//				"Aa123!@#", "ba@ba.com", "6512345678");
//		uService.setNewUserNum(u, "Member");
//		
//		outputStringToConsole(u.toString());
//		
//		// Adding To Database
//		uService.addUser(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenUpdateUser_thenReturnUser() {
//		// Retrieving Existing User Object
//		User u = uService.getUser("M0056");
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
//		uService.updateUser(u);
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenRemoveUser_thenReturnVoid() {
//		// Removing From Database
//		uService.removeUser("M0057");
//		
//		for (User usr:uRepo.findAll()) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenGetMemberListByName_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<User> mList = uService.getMListByName("ra");
//		
//		outputStringToConsole(String.valueOf(mList.size()));
//		
//		for (User usr:mList) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenGetAdminListByName_thenReturnArrayList() {
//		// Retrieving ArrayList
//		ArrayList<User> aList = uService.getAListByName("ra");
//		
//		outputStringToConsole(String.valueOf(aList.size()));
//		
//		for (User usr:aList) {
//			System.out.println(usr.toString());
//		}
//	}
	
//	@Test
//	public void whenGetUserByEmailPw_thenReturnUser() {
//		User u = uService.getUserByEmailPw("ba@ba.com", "Aa123!@#");
//		
//		if (u != null) outputStringToConsole(u.toString());
//		else outputStringToConsole("did not find any matching record");
//	}
	
//	@Test
//	public void whenValidateLogin_thenReturnUser() {
//		outputStringToConsole("Valid Login Scenario");
//		User u = uService.validateLogin("A0004", "T0WtDJm7!");
//		
//		if (u != null) outputStringToConsole(u.toString());
//		else outputStringToConsole("Invalid credentials");
//		
//		outputStringToConsole("Invalid Login Scenario");
//		u = uService.validateLogin("A0003", "T0WtDJm7!");
//		
//		if (u != null) outputStringToConsole(u.toString());
//		else outputStringToConsole("Invalid credentials");
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
