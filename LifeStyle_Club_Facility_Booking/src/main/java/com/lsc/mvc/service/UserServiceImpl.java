package com.lsc.mvc.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository uRepo;
	
	@Override
	public User setNewUserNum(User u, String type) throws UserNotFound {
		if (u == null) throw new UserNotFound("User object provided cannot be null");
		
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
	public User addUser(User user) throws UserNotFound {
		if (user == null) throw new UserNotFound("User object provided cannot be null");
		return uRepo.saveAndFlush(user);
	}
	
	@Override
	@Transactional
	public User getUser(String userNum) throws UserNotFound {
		User u = uRepo.getUserByUserNumber(userNum);
		if (u == null) throw new UserNotFound("User number provided is invalid");
		else return u;
	}

	@Override
	@Transactional
	public User updateUser(User user) throws UserNotFound {
		if (user == null) throw new UserNotFound("User object provided cannot be null");
		return uRepo.saveAndFlush(user);
	}

	@Override
	@Transactional
	public Void removeUser(String userNum) throws UserNotFound {
		if (getUser(userNum) == null) throw new UserNotFound("User number provided is invalid");
		uRepo.delete(getUser(userNum));
		return null;
	}
	
	@Override
	public User updatePassword(String userNum, String pw) throws UserNotFound {
		if (getUser(userNum) == null) throw new UserNotFound("User number provided is invalid");
		
		// Retrieving Existing User Object
		User u = getUser(userNum);
		
		// Update User Object and Database
		u.setPassword(pw);
		return updateUser(u);
	}
	
	@Override
	public ArrayList<User> getUserListByUserNum(String userNum) throws UserNotFound {
		if (getUser(userNum) == null) throw new UserNotFound("User number provided is invalid");
		
		// Retrieving Existing User Object
		User u = getUser(userNum);
		
		// Populating ArrayList
		ArrayList<User> uList = new ArrayList<User>();
		uList.add(u);
		return uList;
	}
	
	@Override
	public ArrayList<User> getMListByName(String mName) throws ResourceDefinitionInvalid {
		if (mName == "") throw new ResourceDefinitionInvalid("Please provide a name to search");
		return uRepo.getMemberListByName(mName);
	}
	
	@Override
	public ArrayList<User> getAListByName(String aName) throws ResourceDefinitionInvalid {
		if (aName == "") throw new ResourceDefinitionInvalid("Please provide a name to search");
		return uRepo.getAdminListByName(aName);
	}
	
	@Override
	public List<User> getMList() {
		return uRepo.findAll().stream().filter(x -> x.getUserNumber().substring(0, 1).equals("M")).collect(Collectors.toList());
	}
	
	@Override
	public List<User> getAList() {
		return uRepo.findAll().stream().filter(x -> x.getUserNumber().substring(0, 1).equals("A")).collect(Collectors.toList());
	}
	
	@Override
	public User getUserByEmailPw(String emailAdd, String pw) throws ResourceDefinitionInvalid {
		// Retrieve ArrayList of Users
		ArrayList<User> uList = (ArrayList<User>) uRepo.findAll();
		if (uList.size() == 0) throw new ResourceDefinitionInvalid("There are no user records");
		
		// Check for any user who matches criteria
		for (User u:uList) {
			if (u.getEmailAddress().equalsIgnoreCase(emailAdd) &&
					u.getPassword().equals(pw))
				return u;
		}
		return null;
	}
	
	@Override
	public User validateLogin(String userNum, String pw) throws UserNotFound {
		if (getUser(userNum) == null) throw new UserNotFound("User number provided is invalid");
		
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
	public String getUserType(String uNum) throws UserNotFound {
		if (getUser(uNum) == null) throw new UserNotFound("User number provided is invalid");
		
		// Return userType based on parsing string
		if (uNum.equals("A0001")) return "SuperAdmin";
		else
			switch (uNum.substring(0,1)) {
				case "A": return "Admin";
				case "M": return "Member";
				default: return "";
			}
	}
	
	@Override
	public Boolean validatePasswordChange(String uNum, String oldPw, String newPw, String confirmPw) throws UserNotFound {
		if (getUser(uNum) == null) throw new UserNotFound("User number provided is invalid");
		
		// Check for blanks
		if (oldPw=="" || newPw=="" || confirmPw=="") return false;
		else {
			// Check that oldPw valid
			if (validateLogin(uNum, oldPw)==null) return false;
			else {
				// Check that newPw equals confirmPw
				if (!(newPw.equals(confirmPw))) return false;
				else {
					// Check newPw complexity
					if (!(checkPwComplexity(newPw))) return false;
					else return true;
				}
			}
		}
	}
	
	@Override
	public List<String> getTitleList() {
		// Create ArrayList
		ArrayList<String> tList = new ArrayList<String>();
		tList.add("Dr"); tList.add("Mdm"); tList.add("Mr");
		tList.add("Mrs"); tList.add("Ms");
		
		return tList;
	}
	
	// Utility Methods
	public Boolean checkPwComplexity(String pw) {
		/*
		 * Criteria:
		 * - At least 8 characters long
		 * - Contains at least 1 digit
		 * - Contains at least 1 lowercase alphabet and 1 uppercase alphabet
		 * - Contains at least 1 symbol
		 * - Does not contain space or tab
		 */
		String regexPw = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		return pw.matches(regexPw);
	}
}
