package com.lsc.mvc.service;

import com.lsc.mvc.model.User;

public interface UserService {
	
	User setNewUserNum(User u, String type);
	
	User addUser(User user);
	
	User getUser(String userNum);

	User updateUser(User user);

	Void removeUser(String userNum);
}
