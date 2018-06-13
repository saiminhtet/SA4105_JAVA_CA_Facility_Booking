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
import com.lsc.mvc.javabeans.AuthenticateUser;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserService usrService;

	@Autowired
	private AuthenticateUser util;

	@GetMapping
	public String Home(ModelMap model, HttpServletRequest req) {
		// model.addAttribute("message", "Hello Spring MVC");

		// Setting up variable to set attributes in view
				String authAdminResult = util.authenticateAdmin(req, model);
				String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
				String authMemberResult = util.authenticateMember(req, model);

				if (authAdminResult.equals("OK"))
					
					return "home/admin_home";
				if (authSuperAdminResult.equals("OK"))
					return "home/super_admin_home";
				if (authMemberResult.equals("OK"))
					return "home/member_home";
				else return "home/login";
	}

	@GetMapping("login")
	public String Login(HttpServletRequest req, ModelMap model) {

		// Setting up variable to set attributes in view
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		//get the session variable 
		HttpSession session = req.getSession();
		String loginUserName = (String) session.getAttribute("userName");		
		if (authAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);
			return "home/admin_home";
		}
		if (authSuperAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);
			return "home/super_admin_home";
		}	
		if (authMemberResult.equals("OK")){
			model.put("loginUserName", loginUserName);
			return "home/member_home";
		}
		return "home/login";
	}

	@PostMapping("login")
	public String vlaidate_Login(HttpServletRequest req, HttpSession session, ModelMap model) {

		User user = new User();
		// Retrieves userNumber from session
		String user_email = req.getParameter("email");
		String user_password = req.getParameter("password");

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

		// Save user info in the session
		String userNumber = user.getUserNumber();
		String userName =  user.getFirstName() + " " + user.getMiddleName() +" "+ user.getLastName();
		session.setAttribute("userNumber", userNumber);
		session.setAttribute("userName", userName);

		System.out.println(user.toString());
		System.out.println(userNumber);
		System.out.println(userName);
		System.out.println(session.getAttribute("userNumber"));
		System.out.println(session.getAttribute("userName"));
		return "home/member_home";
		// return "redirect:/user/profile"; // This means that login success // default
		// redirection for guest/user to retry
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpSession session) {
		try {
			session.invalidate();
			return "home/login";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@GetMapping("/member")
	public String Member(ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginUserName = (String) session.getAttribute("userName");		
		model.put("loginUserName", loginUserName);
		return "home/member_home";
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

	// // Checking User Session and Get User Number
	// public String getUserNumber(HttpServletRequest req) {
	// HttpSession session = req.getSession();
	// String userNumber = (String) session.getAttribute("userNumber");
	//
	// // Sets userNumber to default value if null
	// if (userNumber == null) {
	// // userNumber = "M0065";
	// // session.setAttribute("userNumber", userNumber);
	// // return "user/login";
	// }
	// return userNumber;
	// }

	@GetMapping("searchfacility")
	public String search_facility(Model model) {
		// model.addAttribute("message", "Hello Spring MVC");
		return "facility/search_facility";
	}

	@GetMapping("managefacility")
	public String book_facility(Model model) {
		// model.addAttribute("message", "Hello Spring MVC");

		return "facility/manage_facility";

	}

	@GetMapping("bookingsummary")
	public String booking_summary(Model model) {
		return "booking/booking_summary";

	}

	@GetMapping("managebooking")
	public String manage_booking(Model model) {
		return "booking/manage_booking";
	}

	@GetMapping("searchbooking")
	public String search_booking(Model model) {
		return "booking/search_booking";
	}

}
