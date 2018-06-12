package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.EmailService;
import com.lsc.mvc.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTests {
	
	@Autowired
	private UserService uService;
	@Autowired
	private EmailService eService;
	
	@Test
	public void testNotifyNewUserSignup() {
		try {
			User u = uService.getUser("M0015");
			eService.notifyNewUserSignup(u);
			outputStringToConsole(u.toString());
		} catch (UserNotFound e) {
			outputStringToConsole(e.toString());
		}
	}

	
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg);
		System.out.println("\n*********************************************************\n");
	}

}
