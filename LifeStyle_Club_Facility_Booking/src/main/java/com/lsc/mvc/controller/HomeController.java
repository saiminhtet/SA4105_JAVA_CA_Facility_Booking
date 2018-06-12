package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService usrService;

	// @RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
	// public String testJspView() {
	//
	// return "jsp_test";
	// }

	@GetMapping
	public String index(Model model) {
		// model.addAttribute("message", "Hello Spring MVC");
		return "home/member_home";
	}

	@GetMapping("login")
	public String Login(HttpServletRequest req, ModelMap model) {

		// Retrieves userNumber from session

		String userNumber = this.getUserNumber(req);

		User user = new User();

		// Setting up variable to set attributes in view
		model.put("user", user);
		try {
			user = usrService.getUser(userNumber); // throws UserNotFound

			// At this point, if no exception, userNumber is valid and user object is
			// retrieved
			// Check userType to decide which welcome page to redirect
			String userType = usrService.getUserType(userNumber); // throws UserNotFound
			switch (userType) {
			case "Member":
				return "redirect:/member";
			case "Admin":
				return "redirect:/admin";
			case "SuperAdmin":
				return "redirect:/superadmin";
			default:
				return "home/login";

			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus return to login page
			return "home/login";
		}

	}

	@PostMapping("login")
	public String vlaidate_Login(HttpServletRequest req, HttpSession session, ModelMap model, User user) {

		// Retrieves userNumber from session

		String user_email = user.getEmailAddress();
		String user_password = user.getPassword();
		// Setting the appropriate user data for the user object

		try {
			user = usrService.getUserByEmailPw(user_email, user_password);
		} catch (ResourceDefinitionInvalid e) {
			// need to show error message
			return "home/login";
		}
		// set the user number to validate login
		String login_userNumber = user.getUserNumber();
		String login_password = user.getPassword();

		try {
			user = usrService.validateLogin(login_userNumber, login_password);

		} catch (UserNotFound e) {
			// need to show error message
			return "home/login";
		}
		
		//Save user info in the session
		String userNumber = user.getUserNumber();
		String userName = user.getTitle() + user.getFirstName() + user.getMiddleName() + user.getLastName();
		session.setAttribute("userNumber", userNumber);
		session.setAttribute("userName", userName);
		
		
		System.out.println(user.toString());
		System.out.println(userNumber);
		System.out.println(userName);
		System.out.println(session.getAttribute("userNumber"));
		System.out.println(session.getAttribute("userName"));
		return "home/member_home.html";
	//	return "redirect:/user/profile"; // This means that login success // default redirection for guest/user to retry
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpSession session) {
		try {
			session.invalidate();
			return "redirect:/login";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("member")
	public String Member(Model model, HttpSession session) {
	
//		String userNumber = (String) session.getAttribute("userNumber");
//		model.addAttribute("message", userNumber);
//		System.out.println(userNumber);
		return "home/member_home.html";
	}

	@GetMapping("admin")
	public String Admin(Model model) {
		// model.addAttribute("message", "Hello Spring MVC");
		return "home/admin_home";
	}

	@GetMapping("superadmin")
	public String SuperAdmin(Model model) {
		// model.addAttribute("message", "Hello Spring MVC");
		return "home/super_admin_home";
	}

	// Checking User Session and Get User Number
	public String getUserNumber(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userNumber = (String) session.getAttribute("userNumber");

		// Sets userNumber to default value if null
		if (userNumber == null) {
			// userNumber = "M0065";
			// session.setAttribute("userNumber", userNumber);
			// return "user/login";
		}
		return userNumber;
	}

}
