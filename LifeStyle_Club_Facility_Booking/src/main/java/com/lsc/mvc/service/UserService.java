package com.lsc.mvc.service;

import com.lsc.mvc.model.User;

public interface UserService {
	
	String addUser(User user);
	
	User getUser(String userNum);

	String updateUser(User user);

	String removeUser(String userNum);
}
