package com.lsc.mvc.service;

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
	@Transactional
	public String addUser(User user) {
		return "";
	}
	
	@Override
	@Transactional
	public User getUser(String userNum) {
		return uRepo.getUserByUserNumber(userNum);
	}

	@Override
	@Transactional
	public String updateUser(User user) {
		return "";
	}

	@Override
	@Transactional
	public String removeUser(String userNum) {
		return "";
	}
}
