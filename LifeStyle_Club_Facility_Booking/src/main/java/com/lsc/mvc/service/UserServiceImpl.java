package com.lsc.mvc.service;

import java.text.DecimalFormat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository uRepo;
	
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
}
