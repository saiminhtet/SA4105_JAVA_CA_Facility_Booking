README.TXT
Version 1.0, 2018-Jun-14 (Thu)

==========

1. Introduction

This is the readMe file for the 
SA4105 Club Application for Booking (CAB) project by 
NUS ISS GDipSA46 Team 6.

-----

2. The Team

	2.1 Gao Yangyang						A0180493B
	2.2 Hunasavally Virupaksha Dhanya		A0180525J
	2.3 Jerome Kwek Li Ming					A0180495X
	2.4 Sai Min Htet						A0180545E
	2.5 Tang Shenqi							A0114523U
	2.6 Teong Hanlong						A0055905A

-----

3. Instructions

	3.1 Import the project into workspace 

	3.2 Execute the SQL script to create and populate database

	3.3 Update the Maven Libraries (if needed)
	
	3.4 Check and update mySQL database connection details at 
			src/main/resources/application.properties

	3.4 Compile and run on server. 
		We recommend to run via Boot Dashboard.
		If this view is absent, display it via
			File Menu > Window > Show View > Other... > Spring > Boot Dashboard
	
	3.5 Open browser and load the home URL (http://localhost:8080/) to test the application

-----

4. Major Features

	4.1 User-related 
		- Login, Signup, Reset Password
		- Profile, Change Password, Logout
		
	4.2 Facility-related
		- Add/Search/Update/Delete Facility Records
		- Add/Search/Update/Delete Issue Records
		
	4.3 Booking-related
		- Add/Search/Update/Delete Booking Records
		
	4.4 Unit Testing
		- Source files can be found at
			src/test/java/com.lsc.mvc 				: tests for model, repo, services
			src/test/java/com.lsc.mvc.validator 	: tests for validators
	
	4.5 Views (HTML)
		- Sources files can be found at
			src/main/resources/templates
	
	4.6 Email
		- Source files can be found at
			src/main/java/com.lsc.mvc.util
	
-----

5. Additional Information

	5.1 Club Account Login Credentials
	
		You'll need the following club account login credentials in order 
		to access the various functions accessible by SuperAdmin, Admin and Member roles.
		
		Role			UserNumber		Password
		----------		----------		----------
		SuperAdmin		A0001			Ss123456!
		Admin			A0002			Aa123456!
		Member			M0006			Mm123456!
		
	5.2 Email Account Login Credentials
	
		You'll need the following Gmail account login credentials in order
		to verify the emails which are sent by the system at various triggers which include:
			
			- Upon new account signup
			- Upon reset password request
			- Upon profile update
			- Upon change password request
			- Upon member/admin account expiry
			- Upon booking confirmation
			- Upon booking update
		
		Email Address: 		lifestyleclub.singapore@gmail.com
		Password: 			Cc123456!
		
		Note: This is the default email address that we have set for all accounts 
		in the test data specifically for testing purposes in order not to
		accidentally send spam emails to other people. 