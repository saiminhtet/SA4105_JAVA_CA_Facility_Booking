package com.lsc.mvc.service;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.User;

public interface EmailService {
	
	// notify user number after signup
	void notifyNewUserSignup (User u) throws UserNotFound;
	// notify user after request to reset password
	// need to feed the random generated password
	void notifyResetPassword (User u, String newPassword) throws UserNotFound;
	// notify user after profile update
	void notifyUpdateProfile (User u) throws UserNotFound;
	// notify user afterchange password
	void notifyChangePassword (User u) throws UserNotFound;
	// notify user after make booking
	void notifyBookingSummary (Booking b) throws BookingNotFound, UserNotFound, FacilityNotFound;
	// notify user that the removal succeeded
	void notifyRemoveBooking (Booking b) throws BookingNotFound, UserNotFound, FacilityNotFound;
	// notify user that his account has been deactivated
	void notifyExpireUser (User u ) throws UserNotFound;
}
