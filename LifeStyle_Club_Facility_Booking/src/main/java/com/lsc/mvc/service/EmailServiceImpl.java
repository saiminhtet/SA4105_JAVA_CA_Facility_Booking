package com.lsc.mvc.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.Facility;
import com.lsc.mvc.model.User;
import com.lsc.mvc.util.EmailGenerator;

@Service
public class EmailServiceImpl implements EmailService {
	
	private UserService uService;
	private FacilityService fService;
	
	@Resource
	private EmailGenerator eg;

	@Override
	public void notifyNewUserSignup(User u) throws UserNotFound {
		
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		else {
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] User Registration";
			String bodyText = "You have recently registered for an account with LifeStyleClub and we are glad to have you onboard.\n" + 
						"Your User Number is " + u.getUserNumber() + ".\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyResetPassword(User u, String newPassword) throws UserNotFound {
		
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		else {
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Reset Password";
			String bodyText = "You have required to reset your password.\n" + 
						"Your new password is \"" + newPassword + "\", please use this password and your User Number to login to your account.\n" +
					"You are strongly advised to change your password soon.\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyUpdateProfile(User u) throws UserNotFound {
		
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		else {
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Profile Update";
			String bodyText = "You have recently updated your profile, below is a summary of your user profile.\n" + 
						"Name:\t" + u.getTitle() + " " + u.getFirstName() + " " + u.getMiddleName() + " " + u.getLastName() + "\n" +
					"Email Address:\t" + u.getEmailAddress() + "\n" + "Phone Number:\t" + u.getPhoneNumber() + "\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyChangePassword(User u) throws UserNotFound {
		
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		else {
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Change Password";
			String bodyText = "You have recently changed your password. If this action is not performed by you, please contact our staff for assistance.\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyBookingSummary(Booking b) throws BookingNotFound, UserNotFound, FacilityNotFound {
		
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		else {
			User u = uService.getUser(b);
			Facility f = fService.getFacility(b);
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Booking Summary";
			String bodyText = "You have recently made a booking with us, below is your booking summary.\n" + 
						"Facility Name:\t" + f.getFacilityName() + "\n" +
					"Slot Date:\t" + b.getSlotDate().toString() + "\n" +
						"Slot Start Time:\t" + b.getSlotTimeStart().toString() + "\n" +
					"Slot End Time:\t" + b.getSlotTimeEnd().toString() + "\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyRemoveBooking(Booking b) throws BookingNotFound, UserNotFound, FacilityNotFound {
		
		if (b == null) throw new BookingNotFound("Booking object provided cannot be null");
		else {
			User u = uService.getUser(b);
			Facility f = fService.getFacility(b);
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Booking Cancellation";
			String bodyText = "You have recently cancelled a booking, bellow is the detail.\n" + 
					"Facility Name:\t" + f.getFacilityName() + "\n" +
					"Slot Date:\t" + b.getSlotDate().toString() + "\n" +
						"Slot Start Time:\t" + b.getSlotTimeStart().toString() + "\n" +
					"Slot End Time:\t" + b.getSlotTimeEnd().toString() + "\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

	@Override
	public void notifyExpireUser(User u) throws UserNotFound {
		
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		else {
			String receiverEmailAddress = u.getEmailAddress();
			String subjectText = "[Notification] Account Expired";
			String bodyText = "You account has been deactivated.\n" + 
						"If you wish to activate your account, please contact our staff for assistance.\n";
			eg.generateEmail(receiverEmailAddress, subjectText, bodyText);
		}

	}

}
