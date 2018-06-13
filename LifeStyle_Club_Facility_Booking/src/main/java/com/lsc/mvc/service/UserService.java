package com.lsc.mvc.service;

import java.util.ArrayList;
import java.util.List;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.Booking;
import com.lsc.mvc.model.User;

public interface UserService {
	
	User setNewUserNum(User u, String type) throws UserNotFound;
	
	User addUser(User user) throws UserNotFound;
	
	User getUser(String userNum) throws UserNotFound;

	User updateUser(User user) throws UserNotFound;

	Void removeUser(String userNum) throws UserNotFound;
	
	User updatePassword(String userNum, String pw) throws UserNotFound;
	
	ArrayList<User> getUserListByUserNum(String userNum) throws UserNotFound;
	
	ArrayList<User> getMListByName(String mName) throws ResourceDefinitionInvalid;
	
	ArrayList<User> getAListByName(String aName) throws ResourceDefinitionInvalid;
	
	List<User> getMList();
	
	List<User> getAList();
	
	User getUserByEmailPw(String emailAdd, String pw) throws ResourceDefinitionInvalid;
	
	User validateLogin(String userNum, String pw) throws UserNotFound;
	
	User validateEmail(String emailAdd);
	
	String getUserType(String uNum) throws UserNotFound;
	
	Boolean validatePasswordChange(String uNum, String oldPw, String newPw, String confirmPw) throws UserNotFound;
	
	List<String> getTitleList();
	
	User getUser(Booking b) throws BookingNotFound, UserNotFound;
	
	String generateRandomPw();
}
