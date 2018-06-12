package com.lsc.mvc.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGenerator {
	
	public static void generateEmail(String receiverEmailAddress, String subject, String actualMessage) {
		
		String to = receiverEmailAddress;
		
	    String from = "lifestyleclub.singapore@gmail.com";

		Properties props = new Properties();
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    
	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	               protected PasswordAuthentication getPasswordAuthentication() {
	                  return new PasswordAuthentication(
	                     "lifestyleclub.singapore@gmail.com", "Cc123456!");
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
	       message.setSubject(subject);

	         // Now set the actual message
	       message.setText(actualMessage);

	         // Send message
	       Transport.send(message);
//	       System.out.println("Sent message successfully....");
	    } 
	    catch (MessagingException mex) {
	       mex.printStackTrace();
	    }
	}
}
