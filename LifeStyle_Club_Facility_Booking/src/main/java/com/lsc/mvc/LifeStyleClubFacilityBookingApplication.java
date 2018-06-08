package com.lsc.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

<<<<<<< HEAD
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
=======
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
>>>>>>> refs/remotes/origin/master
public class LifeStyleClubFacilityBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeStyleClubFacilityBookingApplication.class, args);
	}
}
