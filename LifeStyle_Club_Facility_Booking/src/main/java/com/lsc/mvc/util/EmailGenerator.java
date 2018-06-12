package com.lsc.mvc.util;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGenerator {
	
	public void generateEmail(String receiverEmailAddress, String subjectText, String bodyText) {
		
		// Sender Email Credentials
		String senderEmailAddress = "lifestyleclub.singapore@gmail.com";
		String senderPw = "Cc123456!";
		
		String to = receiverEmailAddress;
	    String from = senderEmailAddress;

		Properties props = new Properties();
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	    	protected PasswordAuthentication getPasswordAuthentication() {
	    		return new PasswordAuthentication(senderEmailAddress, senderPw);
	    	}
	    });

	    try {
	    	// Create a default MimeMessage object.
	    	MimeMessage message = new MimeMessage(session);
	    	
	    	// Set From: header field of the header.
	    	message.setFrom(new InternetAddress(from));
	    	
	    	// Set To: header field of the header.
	    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	    	
	    	// Set Subject: header field
	    	message.setSubject(subjectText);
	    	
	    	// Defining Sign-off
	    	String footerText = "\n\n--------------------\n\n"
	    			+ "Please do not reply to this message. \r\n"
	    			+ "This email was sent from a notification-only email address that does not accept incoming email. \r\n" + 
	    			"Feel free to contact your personal concierge at +65 9123 4567 for immediate assistance, or \r\n" + 
	    			"send us an email at support@lifestyle.sg and we will serve you shortly.\r\n" + 
	    			"\r\n\n" +
	    			"2018 Lifestyle Club. All rights reserved.";
	    	
	    	// Now set the actual message
	    	message.setText(bodyText + footerText);
	    	
	    	// Send message
	    	Transport.send(message);
	    	
	    	// Update Log
	    	System.out.println("\n"
	    			+ "Email has been sent successfully. \n\n"
	    			+ "From: " + senderEmailAddress + "\n"
	    			+ "To: " +  receiverEmailAddress + "\n"
	    			+ "Sent At: " + LocalDateTime.now() + "\n"
	    			+ "Subject: " + subjectText + "\n"
	    			+ "Body: " + bodyText + "\n"
	    			+ "Footer: " + footerText
	    			);
	    } 
	    catch (MessagingException mex) {
	    	mex.printStackTrace();
	    }
	}
}
