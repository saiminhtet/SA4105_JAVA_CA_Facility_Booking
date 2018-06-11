package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;
import com.lsc.mvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usrService;

	@GetMapping
	public void get(ModelMap model) {

	}

	@GetMapping("/signup")
	public String Signup(ModelMap model) {
		User user = new User();
		user.setPassword("enter password");
		model.put("user", user);
		return "user/signup";
	}

	@PostMapping("/adduser")
	public String addNewUser(User user) {
		usrService.setNewUserNum(user, "Member");
		usrService.addUser(user);
		System.out.println(user.toString());
		return "user/signup";
	}

	@GetMapping("/profile")
	public String userProfile(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}

		model.addAttribute("user", usrService.getUser(userNumber));
		return "user/profile";
	}

	@PostMapping("/updateprofile")
	public String updateProfile(HttpServletRequest req, HttpServletResponse res, User user) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}
        User existinguser = new User();
		existinguser = usrService.getUser(userNumber);		
		existinguser.setEmailAddress(user.getEmailAddress());
		existinguser.setPhoneNumber(user.getPhoneNumber());
		
		System.out.println(existinguser.toString());
		usrService.updateUser(existinguser);
		return "user/profile";
	}
	@GetMapping("/changepassword")
	public String getAllMembers(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}

		//model.addAttribute("user", usrService.getUser(userNumber));
		return "user/changepassword";
	}


	
	
	
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	

}
