package com.lsc.mvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lsc.mvc.util.EmailGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailGeneratorTest {
	
	@Test
	public void whenGenerateEmail_thenReturnVoid() {
		String receiverEmailAddress = "lifestyleclub.singapore@gmail.com";
		String subjectText = "[Notification] LifeStyleClub Application";
		String bodyText = "You have recently registered for an account with LifeStyleClub and we are glad to have you onboard.";
		EmailGenerator eg  = new EmailGenerator();
		eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
	}
}
