package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usrService;

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) throws UserNotFound {

		// Retrieves userNumber from session
		String userNumber = this.getUserNumber(req);	
		
		// Check if logged in
		if (userNumber == null) return "redirect:user/login";
		else {
			// Check if userNumber valid
			User user = new User();
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound
				
				// At this point, if no exception, userNumber is valid and user object is retrieved
				// Check userType to decide which welcome page to redirect
				String userType = usrService.getUserType(userNumber); // throws UserNotFound
				switch (userType) {
					case "Member": return "redirect:home/member_home";
					case "Admin": return "redirect:home/admin_home";
					case "SuperAdmin": return "redirect:home/super_admin_home";
					default: return "user/signup";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:user/login";

			}
		}
	}

	@GetMapping("/login")

	public String Login(HttpServletRequest req, ModelMap model) {
		
		// Retrieves userNumber from session

		String userNumber = this.getUserNumber(req);
		
		User user = new User();

	
		try {
			user = usrService.getUser(userNumber); // throws UserNotFound
			
			// At this point, if no exception, userNumber is valid and user object is retrieved
			// Check userType to decide which welcome page to redirect
			String userType = usrService.getUserType(userNumber); // throws UserNotFound
			switch (userType) {
				case "Member": return "redirect:home/member_home";
				case "Admin": return "redirect:home/admin_home";
				case "SuperAdmin": return "redirect:home/super_admin_home";
				default: return "user/login";

			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus return to login page
			return "redirect:user/login";
		}
	}

	@GetMapping("/signup")
	public String Signup(ModelMap model) {
		User user = new User();
		user.setPassword("enter password");
		model.put("title", usrService.getTitleList());
		model.put("user", user);
		return "user/signup";
	}

	@PostMapping("/signup")
	public String addNewUser(HttpServletRequest req, User user) throws ResourceDefinitionInvalid, UserNotFound {
		// Retrieves userNumber from session
		UserValidator uservalidate = new UserValidator();
		String userNumber = this.getUserNumber(req);

		String usertype = usrService.getUserType(userNumber);
		if (usertype == null) {
			usertype = "Member";
		}
		usrService.setNewUserNum(user, usertype);
		//create data to validate
		User user_to_validate = new User();
		user_to_validate.setUserNumber(user.getUserNumber());
		user_to_validate.setTitle(user.getTitle());
		user_to_validate.setFirstName(user.getFirstName());
		user_to_validate.setLastName(user.getLastName());
		user_to_validate.setMiddleName(user.getMiddleName());
		user_to_validate.setPassword(user.getPassword());
		user_to_validate.setEmailAddress(user.getEmailAddress());
		user_to_validate.setPhoneNumber(user.getPhoneNumber());
		
		
		// Validation Using UserValidator Class
		//create databinder to bind data and validator
		DataBinder binder = new DataBinder(user_to_validate);
		binder.setValidator(uservalidate);
		
		//validate data
		binder.validate();
		
		// check results
		BindingResult results = binder.getBindingResult();
		System.out.println(user_to_validate.toString());
		if (results.hasErrors()) {
			throw new ResourceDefinitionInvalid();
		} else {
			usrService.addUser(user);
			System.out.println(user.toString());

			return "user/login";
		}
	}

	@GetMapping("/profile")
	public String userProfile(HttpServletRequest req, ModelMap model) throws UserNotFound {
		String userNumber = this.getUserNumber(req);
		if (userNumber == null) {
			
			return "user/login";
		}
		model.addAttribute("user", usrService.getUser(userNumber));
		return "user/profile";
	}

	@PostMapping("/profile")
	public String updateProfile(HttpServletRequest req, HttpServletResponse res, User user) throws ResourceDefinitionInvalid, UserNotFound {
		// Retrieves userNumber from session

		String userNumber = this.getUserNumber(req);
		if (userNumber == null) {
			return "user/login";
		}
		User existinguser = new User();
		existinguser = usrService.getUser(userNumber);
		existinguser.setEmailAddress(user.getEmailAddress());
		existinguser.setPhoneNumber(user.getPhoneNumber());
		// Validation Using UserValidator Class
		System.out.println(existinguser.toString());
		UserValidator uservalidate = new UserValidator();
		DataBinder binder = new DataBinder(existinguser);
		binder.setValidator(uservalidate);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		if (results.hasErrors()) {
			return "user/profile";
		} else {
			
			usrService.updateUser(existinguser);
			return "user/profile";
		}
	}

	@GetMapping("/changepassword")
	public String changePassword(HttpServletRequest req, ModelMap model) {
		String userNumber = this.getUserNumber(req);
		if (userNumber == null) {
			return "user/login";
		}
		return "user/changepassword";
	}

	@PostMapping("/changepassword")
	public String updatePassword(HttpServletRequest req, ModelMap model) {

		String userNumber = this.getUserNumber(req);
		if (userNumber == null) {
			return "user/login";
		}
		String currentpassword = req.getParameter("current_password");
		String newpassword = req.getParameter("password");
		String confirmpassword = req.getParameter("confirm_password");
		try {
			if (usrService.validatePasswordChange(userNumber, currentpassword, newpassword, confirmpassword)) {
				User existinguser = new User();
				existinguser = usrService.getUser(userNumber);

				System.out.println(existinguser.toString());

				existinguser = usrService.updatePassword(userNumber, newpassword);

				System.out.println(existinguser.toString());

				return "user/profile";
			}
		} catch (UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("type", "error");
		return "user/changepassword";
	}
	
	
	
	//get member list and bind data to manage member view
//	public String getMembers(HttpServletRequest req) {
//		
//	}
//	
	
	

	// Checking User Session and Get User Number
	public String getUserNumber(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			 userNumber = "M0065";
			 session.setAttribute("userNumber", userNumber);
			//return "user/login";
		}
		return userNumber;
	}

	@ModelAttribute("user")
	public User user() {
		return new User();
	}

}
