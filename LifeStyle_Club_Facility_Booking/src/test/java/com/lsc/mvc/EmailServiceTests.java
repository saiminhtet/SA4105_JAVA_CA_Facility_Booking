package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.BookingService;
import com.lsc.mvc.service.EmailService;
import com.lsc.mvc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTests {
	
	@Autowired
	private UserService uService;
	@Autowired
	private BookingService bService;
	@Autowired
	private EmailService eService;
	
	@Test
	public void testNotifyNewUserSignup() {
		try {
			User u = uService.getUser("M0015");	
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyNewUserSignup(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyResetPassword() {
		try {
			User u = uService.getUser("M0015");	
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyResetPassword(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyUpdateProfile() {
		try {
			User u = uService.getUser("M0015");	
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyUpdateProfile(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyChangePassword() {
		try {
			User u = uService.getUser("M0015");	
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyChangePassword(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyBookingSummary() throws BookingNotFound, FacilityNotFound {
		try {
			Booking b = bService.getBooking("B000018");
			
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			User u = uService.getUser(b);
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyBookingSummary(b);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyRemoveBooking() throws BookingNotFound, FacilityNotFound {
		try {
			Booking b = bService.getBooking("B000018");
			
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			User u = uService.getUser(b);
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyRemoveBooking(b);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	@Test
	public void testNotifyExpireUser() {
		try {
			User u = uService.getUser("M0015");	
			// In order to verify success and not randomly send emails to others
			// This is not for production implementation
			u.setEmailAddress("lifestyleclub.singapore@gmail.com");
			
			eService.notifyExpireUser(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.getMessage());
		}
	}
	
	
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg);
		System.out.println("\n*********************************************************\n");
	}

}
