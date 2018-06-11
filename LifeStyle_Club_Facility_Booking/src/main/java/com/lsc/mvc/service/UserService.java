package com.lsc.mvc.service;

import java.util.ArrayList;

import com.lsc.mvc.model.User;

public interface UserService {
	
	User setNewUserNum(User u, String type);
	
	User addUser(User user);
	
	User getUser(String userNum);

	User updateUser(User user);

	Void removeUser(String userNum);
	
	User updatePassword(String userNum, String pw);
	
	ArrayList<User> getUserListByUserNum(String userNum);
	
	ArrayList<User> getMListByName(String mName);
	
	ArrayList<User> getAListByName(String aName);
	
	User getUserByEmailPw(String emailAdd, String pw);
	
	User validateLogin(String userNum, String pw);
	
	User validateEmail(String emailAdd);
	
	String getUserType(String uNum);
	
	Boolean validatePasswordChange(String uNum, String oldPw, String newPw, String confirmPw);
	
//	Boolean checkPwComplexity(String pw);
}
