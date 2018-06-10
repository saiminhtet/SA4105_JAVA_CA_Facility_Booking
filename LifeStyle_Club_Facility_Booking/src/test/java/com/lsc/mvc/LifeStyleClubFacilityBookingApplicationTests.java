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
public class LifeStyleClubFacilityBookingApplicationTests {
	
	@Autowired
	private UserRepository uRepo;
	
	
	
	// Utility Methods
	public void outputStringToConsole(String msg) {
		System.out.println("\n*********************************************************\n");
		System.out.println(msg.toString());
		System.out.println("\n*********************************************************\n");
	}
}
