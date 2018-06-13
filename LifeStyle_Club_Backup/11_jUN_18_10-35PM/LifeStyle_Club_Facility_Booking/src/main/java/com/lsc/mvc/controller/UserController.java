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

	@Autowired
	private UserRepository repo;

	@GetMapping
	public String get(ModelMap model) {
		return "user/signup";
	}

	@GetMapping("/signup")
	public String Login(ModelMap model) {
		User user = new User();
		user.setPassword("enter password");
		model.put("user", user);
		return "user/login";
	}
	
	
	
	
	@GetMapping("/signup")
	public String Signup(ModelMap model) {
		User user = new User();
		user.setPassword("enter password");
		model.put("user", user);
		return "user/signup";
	}

	@PostMapping("/signup")
	public String addNewUser(HttpServletRequest req, User user) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}
		String usertype = usrService.getUserType(userNumber);
		usrService.setNewUserNum(user, usertype);
		usrService.addUser(user);
		System.out.println(user.toString());
		return "user/login";
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

	@PostMapping("/profile")
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
	public String changePassword(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		// Retrieves userNumber from session
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}

		return "user/changepassword";
	}

	@PostMapping("/changepassword")
	public String updatePassword(HttpServletRequest req, ModelMap model) {
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			userNumber = "M0065";
			session.setAttribute("userNumber", userNumber);
		}
		String currentpassword = req.getParameter("current_password");
		String newpassword = req.getParameter("password");
		String confirmpassword = req.getParameter("confirm_password");
		if (usrService.validatePasswordChange(userNumber, currentpassword, newpassword, confirmpassword)) {
			User existinguser = new User();
			existinguser = usrService.getUser(userNumber);

			System.out.println(existinguser.toString());

			existinguser = usrService.updatePassword(userNumber, newpassword);

			System.out.println(existinguser.toString());

			return "user/profile";
		}
		model.put("type", "error");
		return "user/changepassword";
	}

	@ModelAttribute("title")
	public Title[] genders() {
		return Title.values();
	}

	public enum Title {
		Dr, Mr, Mrs
	}

	@ModelAttribute("user")
	public User user() {
		return new User();
	}

}
