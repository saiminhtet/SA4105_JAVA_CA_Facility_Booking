package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository uRepo;
	
	@Override
	public User setNewUserNum(User u, String type) {
		// Getting Current Max userId
		Integer newId = uRepo.getUserIdMax() + 1;
		
		// Assigning New userNumber
		DecimalFormat fmt = new DecimalFormat("0000");
		String prefix = (type =="Admin")? "A" : "M";
		u.setUserNumber(prefix + fmt.format(newId));
		return u;
	}
	
	@Override
	@Transactional
	public User addUser(User user) {
		return uRepo.saveAndFlush(user);
	}
	
	@Override
	@Transactional
	public User getUser(String userNum) {
		return uRepo.getUserByUserNumber(userNum);
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		return uRepo.saveAndFlush(user);
	}

	@Override
	@Transactional
	public Void removeUser(String userNum) {
		uRepo.delete(getUser(userNum));
		return null;
	}
	
	@Override
	public User updatePassword(String userNum, String pw) {
		// Retrieving Existing User Object
		User u = getUser(userNum);
		
		// Update User Object and Database
		u.setPassword(pw);
		return updateUser(u);
	}
	
	@Override
	public ArrayList<User> getUserListByUserNum(String userNum) {
		// Retrieving Existing User Object
		User u = getUser(userNum);
		
		// Populating ArrayList
		ArrayList<User> uList = new ArrayList<User>();
		uList.add(u);
		return uList;
	}
	
	@Override
	public ArrayList<User> getMListByName(String mName) {
		return uRepo.getMemberListByName(mName);
	}
	
	@Override
	public ArrayList<User> getAListByName(String aName) {
		return uRepo.getAdminListByName(aName);
	}
	
	@Override
	public User getUserByEmailPw(String emailAdd, String pw) {
		// Retrieve ArrayList of Users
		ArrayList<User> uList = (ArrayList<User>) uRepo.findAll();
		
		// Check for any user who matches criteria
		for (User u:uList) {
			if (u.getEmailAddress().equalsIgnoreCase(emailAdd) &&
					u.getPassword().equals(pw))
				return u;
		}
		return null;
	}
	
	@Override
	public User validateLogin(String userNum, String pw) {
		// Retrieve ArrayList of Users
		ArrayList<User> uList = (ArrayList<User>) uRepo.findAll();
		
		// Check for any user who matches criteria
		for (User u:uList) {
			if (u.getUserNumber().equalsIgnoreCase(userNum) &&
					u.getPassword().equals(pw))
				return u;
		}
		return null;
	}
	
	@Override
	public User validateEmail(String emailAdd) {
		return uRepo.getUserByEmailAddress(emailAdd);
	}
	
	@Override
	public String getUserType(String uNum) {
		// Check if userNumber valid
		if (getUser(uNum)==null) return "";
		
		// Return userType based on parsing string
		if (uNum.equals("A0001")) return "SuperAdmin";
		else
			switch (uNum.substring(0,1)) {
				case "A": return "Admin";
				case "M": return "Member";
				default: return "";
			}
	}
}
