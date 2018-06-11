package com.lsc.mvc;

import java.util.ArrayList;
import java.util.List;

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
//		User u = uService.getUser("M0054");
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
//		User u = uService.getUser("M0055");
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
//		else outputStringToConsole("no matching record");
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
	
//	@Test
//	public void whenValidateByEmail_thenReturnUser() {
//		User u = uService.validateEmail("Jasper.Herrod@gmail.com");
//		
//		if (u != null) outputStringToConsole(u.toString());
//		else outputStringToConsole("no matching record");
//	}
	
//	@Test
//	public void whenGetUserType_thenReturnUser() {
//		String s = uService.getUserType("M0039");
//		
//		if (s != "") outputStringToConsole(s.toString());
//		else outputStringToConsole("no matching record");
//		
//		s = uService.getUserType("A0001");
//		
//		if (s != "") outputStringToConsole(s.toString());
//		else outputStringToConsole("no matching record");
//		
//		s = uService.getUserType("A0050");
//		
//		if (s != "") outputStringToConsole(s.toString());
//		else outputStringToConsole("no matching record");
//	}
	
//	@Test
//	public void whenCheckPwComplexity_thenReturnBoolean() {
//		/*
//		 * Criteria:
//		 * - At least 8 characters long
//		 * - Contains at least 1 digit
//		 * - Contains at least 1 lowercase alphabet and 1 uppercase alphabet
//		 * - Contains at least 1 symbol
//		 * - Does not contain space or tab
//		 */
//		String s;
//		System.out.println("Checking for Blank: Expect false");
//		s = "";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Length: Expect false");
//		s = "1aA!";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Digit: Expect false");
//		s = "Aa!aaaaa";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Lowercase Alphabet: Expect false");
//		s = "1!AAA123";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Uppercase Alphabet: Expect false");
//		s = "1!aaa123";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Symbol: Expect false");
//		s = "1Aaaa123";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Space: Expect false");
//		s = "1A!a a123";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//		
//		System.out.println("Checking for Ideal: Expect true");
//		s = "Aa123!@#";
//		outputStringToConsole(String.valueOf(uService.checkPwComplexity(s)));
//	}
	
//	@Test
//	public void whenValidatePasswordChange_thenReturnBoolean() {
//		String uNum, oldPw, newPw, confirmPw;
//		
//		System.out.println("Checking for Blank: Expect false");
//		uNum = "M0054";
//		oldPw = "KJ8ew2rg!";
//		newPw = "Aa123!@#";
//		confirmPw = "";
//		outputStringToConsole(
//			String.valueOf(uService.validatePasswordChange(uNum, oldPw, newPw, confirmPw))
//		);
//		
//		System.out.println("Checking that oldPw valid: Expect false");
//		uNum = "M0054";
//		oldPw = "KJ8ew2rg!123";
//		newPw = "Aa123!@#";
//		confirmPw = newPw;
//		outputStringToConsole(
//			String.valueOf(uService.validatePasswordChange(uNum, oldPw, newPw, confirmPw))
//		);
//		
//		System.out.println("Checking that newPw equals confirmPw: Expect false");
//		uNum = "M0054";
//		oldPw = "KJ8ew2rg!";
//		newPw = "Aa123!@#";
//		confirmPw = newPw + "123";
//		outputStringToConsole(
//			String.valueOf(uService.validatePasswordChange(uNum, oldPw, newPw, confirmPw))
//		);
//		
//		System.out.println("Checking that newPw complex: Expect false");
//		uNum = "M0054";
//		oldPw = "KJ8ew2rg!";
//		newPw = "AA123!@#";
//		confirmPw = newPw;
//		outputStringToConsole(
//			String.valueOf(uService.validatePasswordChange(uNum, oldPw, newPw, confirmPw))
//		);
//		
//		System.out.println("Checking ideal: Expect true");
//		uNum = "M0054";
//		oldPw = "KJ8ew2rg!";
//		newPw = "Aa123!@#";
//		confirmPw = newPw;
//		outputStringToConsole(
//			String.valueOf(uService.validatePasswordChange(uNum, oldPw, newPw, confirmPw))
//		);
//	}
	
//	@Test
//	public void whenGetTitleList_thenReturnList () {
//		List<String> tList = uService.getTitleList();
//		for (String t:tList) {
//			System.out.println(t.toString());
//		}
//	}
	
//	@Test
//	public void whenGetMList_thenReturnList () {
//		List<User> uList = uService.getMList();
//		
//		System.out.println(String.valueOf(uList.size()));
//		
//		for (User u:uList) {
//			System.out.println(u.toString());
//		}
//	}
	
//	@Test
//	public void whenGetAList_thenReturnList () {
//		List<User> uList = uService.getAList();
//		
//		System.out.println(String.valueOf(uList.size()));
//		
//		for (User u:uList) {
//			System.out.println(u.toString());
//		}
//	}
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
