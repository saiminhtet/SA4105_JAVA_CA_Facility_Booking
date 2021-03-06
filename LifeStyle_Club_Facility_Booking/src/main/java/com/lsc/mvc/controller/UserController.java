package com.lsc.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.boot.model.relational.Database;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.javabeans.AuthenticateUser;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.EmailService;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usrService;

	@Autowired
	private AuthenticateUser util;

	@Autowired
	private EmailService eMailService;

	@GetMapping
	public String Get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		// Set the variables to authenticate user	
		
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		if (authAdminResult.equals("OK") || (authSuperAdminResult.equals("OK") || authMemberResult.equals("OK"))) {

			String userNumber = util.getUNum(req); // to check user is login or not
			String userName = util.getUserName(req);
			try {
				// throws UserNotFound

				String AccountType = usrService.getUserType(util.getUNum(req)); // Get user type for displaying
																				// different menu
				// At this point, if no exception, userNumber is valid and user object is
				// retrieved
				// Check user to decide which profile page to redirect

				model.put("loginUserName", userName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type

				switch (AccountType) {
				case "Member":
					return "home/member_home";
				case "Admin":
					return "home/admin_home";
				case "SuperAdmin":
					return "home/super_admin_home";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}
	}
		return "redirect:/login";
	}

	@PostMapping
	public String postUserPage(HttpServletRequest req, ModelMap model) {	
		
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		if (authAdminResult.equals("OK") || (authSuperAdminResult.equals("OK") || authMemberResult.equals("OK"))) {

			String userNumber = util.getUNum(req); // to check user is login or not
			String userName = util.getUserName(req);
			try {
				// throws UserNotFound

				String AccountType = usrService.getUserType(util.getUNum(req)); // Get user type for displaying
																				// different menu
				// At this point, if no exception, userNumber is valid and user object is
				// retrieved
				// Check user to decide which profile page to redirect

				model.put("loginUserName", userName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type

				switch (AccountType) {
				case "Member":
					return "home/member_home";
				case "Admin":
					return "home/admin_home";
				case "SuperAdmin":
					return "home/super_admin_home";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}
		}

		return "redirect:/login";
	}

	@GetMapping("signup")
	public String getSignUp(HttpServletRequest req, ModelMap model) {
		util.checkMakeAcctType(req, model);
		User user = new User();
		user.setPassword("A1S2d3&!@#$%\r\n");
		model.put("acctType", "Member");
		model.put("title", usrService.getTitleList());
		model.put("user", user);
		return "user/signup";
	}

	@PostMapping("/signup")
	public String addNewUser(HttpServletRequest req, User user) {

		// Determining the accountType for the signup
		String acctType;

		try {
			String userType = usrService.getUserType(util.getUNum(req)); // throws UserNotFound
			switch (userType) {
			case "Member":
				return "redirect:/member"; // Member is not supposed to be able to sign up for other accounts
			case "Admin":
				acctType = "Member"; // Admin is allowed to sign up for member, e.g. guest request counter staff to
				break; // sign up for them
			case "SuperAdmin":
				acctType = "Admin"; // SuperAdmin is allowed to sign up for admin
				break;
			default:
				acctType = "Member"; // Guests are allowed to sign up member accounts
			}
		} catch (UserNotFound e) {
			// This means that userNumber is invalid, thus default
			acctType = "Member";
		}

		// Setting the appropriate userNumber for the user object based on acctType
		try {
			usrService.setNewUserNum(user, acctType); // throws UserNotFound
		} catch (UserNotFound e) {
			// This means that the "user" object passed into this method is null, thus
			// redirect back to signup page to restart
			return "user/signup";
		}
		System.out.println(acctType + ", " + user.toString());
		// Perform validation Using UserValidator Class
		// Create UserValidator
		UserValidator uservalidator = new UserValidator();

		// Create DataBinder to Bind UserValidator
		DataBinder binder = new DataBinder(user);
		binder.setValidator(uservalidator);

		// Validate the Data
		binder.validate();

		// Check Results
		BindingResult results = binder.getBindingResult();
		System.out.println(user.toString());
		System.out.println(results.toString());
		if (results.hasErrors()) {
			// Provide feedback to user that an error has occurred and what are the actions
			// that can be taken
			return "user/signup"; // default redirection for guest/user to retry
		} else {
			// This means that there is no validation error
			try {
				usrService.addUser(user); // throws UserNotFound : this is to catch is the user object passed to the
				eMailService.notifyNewUserSignup(user); // sending email for new user
				System.out.println(user.toString());// addUser method is null
				return "redirect:/login"; // This means that sign-up success
			} catch (UserNotFound e) {
				System.out.println(e.getMessage());
			}
		}
		return "user/signup"; // default redirection for guest/user to retry
	}

	@GetMapping("/profile")
	public String userProfile(HttpServletRequest req, ModelMap model, User user) {
		// Authenticate User
		// Set the variables to authenticate user
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		if (authAdminResult.equals("OK") || (authSuperAdminResult.equals("OK") || authMemberResult.equals("OK"))) {

			String userNumber = util.getUNum(req); // to check user is login or not
			String userName = util.getUserName(req);
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound

				String AccountType = usrService.getUserType(util.getUNum(req)); // Get user type for displaying
																				// different menu
				// At this point, if no exception, userNumber is valid and user object is
				// retrieved
				// Check user to decide which profile page to redirect
				if (user != null) {
					model.put("loginUserName", userName);// to view user session name in home page
					model.put("MenuType", AccountType); // Set menu type by user type
					model.addAttribute("user", user);
					switch (AccountType) {
					case "Member":
						return "user/profile";
					case "Admin":
						return "user/profile";
					case "SuperAdmin":
						return "user/profile";
					}
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}
		}
		return "redirect:/login";

	}

	@PostMapping("/updateprofile")
	public String updateProfile(HttpServletRequest req, ModelMap model, User user) {
		// Authenticate User
		// Set the variables to authenticate user
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		if (authAdminResult.equals("OK") || (authSuperAdminResult.equals("OK") || authMemberResult.equals("OK"))) {
			// Retrieves userNumber from session
			String userNumber = util.getUNum(req);// to check user session
			User existinguser = new User();
			try {
				existinguser = usrService.getUser(userNumber);
				user.setPassword(existinguser.getPassword());
				user.setUserId(existinguser.getUserId());
				String AccountType = usrService.getUserType(util.getUNum(req)); // Get user type for displaying
																				// different menu
				System.out.println(existinguser);
			} catch (UserNotFound e1) {
				// if user not found
				return "redirect:/login";
			}
			System.out.println(existinguser.toString());
			// Validation Using UserValidator Class
			// Perform validation Using UserValidator Class
			// Create UserValidator
			UserValidator uservalidator = new UserValidator();
			// Create DataBinder to Bind UserValidator
			DataBinder binder = new DataBinder(user);
			binder.setValidator(uservalidator);
			// Validate the Data
			binder.validate();
			// Check Results
			System.out.println(user.toString());
			BindingResult results = binder.getBindingResult();
			System.out.println(user.toString());
			System.out.println(results.toString());
			if (results.hasErrors()) {
				// Provide feedback to user that an error has occurred and what are the actions
				// that can be taken
				return "redirect:/"; // default redirection for guest/user to retry
			} else {
				// This means that there is no validation error
				try {
					// throws UserNotFound : this is to catch is the user object passed to the
					usrService.updateUser(user);
					eMailService.notifyUpdateProfile(user); // sending email for new user
					System.out.println(user.toString());
					return "redirect:/"; // This means that sign-up success
				} catch (UserNotFound e) {
					System.out.println(e.getMessage());
				}
			}
		}
		// return "user/profile"; // default redirection for guest/user to retry
		return "redirect:/";
	}

	@GetMapping("/changepassword")
	public String changePassword(HttpServletRequest req, ModelMap model) {
		// Setting up variable to set attributes in view
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		if (authAdminResult.equals("OK") || (authSuperAdminResult.equals("OK") || authMemberResult.equals("OK"))) {

			String userNumber = util.getUNum(req); // to check user is login or not
			String userName = util.getUserName(req);
			try {
				// throws UserNotFound

				String AccountType = usrService.getUserType(util.getUNum(req)); // Get user type for displaying
																				// different menu
				// At this point, if no exception, userNumber is valid and user object is
				// retrieved
				// Check user to decide which profile page to redirect

				model.put("loginUserName", userName);// to view user session name in home page
				model.put("MenuType", AccountType); // Set menu type by user type

				switch (AccountType) {
				case "Member":
					return "user/changepassword";
				case "Admin":
					return "user/changepassword";
				case "SuperAdmin":
					return "user/changepassword";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}
		}

		return "redirect:/login";

	}

	@PostMapping("/changepassword")
	public String updatePassword(HttpServletRequest req, ModelMap model) {

		String userNumber = util.getUNum(req);

		if (userNumber == null) {
			return "user/login";
		} else {
			// Check if userNumber valid
			User user = new User();
			try {
				user = usrService.getUser(userNumber); // throws UserNotFound
				userNumber = user.getUserNumber();
				String currentpassword = req.getParameter("current_password");
				String newpassword = req.getParameter("password");
				String confirmpassword = req.getParameter("confirm_password");
				usrService.validatePasswordChange(userNumber, currentpassword, newpassword, confirmpassword);
				User existinguser = new User();
				existinguser = usrService.getUser(userNumber);
				existinguser = usrService.updatePassword(userNumber, newpassword);
				eMailService.notifyChangePassword(existinguser); // sending email for new user
				User updateuser = usrService.getUser(userNumber);
				System.out.println(updateuser.toString());

				// Setting up variable to set attributes in view
				String authAdminResult = util.authenticateAdmin(req, model);
				String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
				String authMemberResult = util.authenticateMember(req, model);

				// get the session variable
				String loginUserName = util.getUserName(req);
				if (authAdminResult.equals("OK")) {
					model.put("loginUserName", loginUserName);// to view user session name in home page
					return "redirect:/admin";
				}
				if (authSuperAdminResult.equals("OK")) {
					model.put("loginUserName", loginUserName);// to view user session name in home page
					return "redirect:/superadmin";
				}
				if (authMemberResult.equals("OK")) {
					model.put("loginUserName", loginUserName);// to view user session name in home page
					return "redirect:/member";
				}
			} catch (UserNotFound e) {
				// This means that userNumber is invalid, thus return to login page
				return "redirect:/login";
			}
		}
		return "redirect:/login";
	}

	// get member list and bind data to manage member view
	@GetMapping("searchmember")
	public String getMembers(ModelMap model, HttpServletRequest req) {
		String authAdminResult = util.authenticateAdmin(req, model);
		
		String loginUserName = util.getUserName(req);
		if (authAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			model.put("memberlist", usrService.getMList());
			return "user/search_member";
		} else {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return authAdminResult;}
		
	}

	@PostMapping("searchmember")
	public String searchMemer(HttpServletRequest req, ModelMap model) {
		String userNumber = req.getParameter("membernumber");
		String userName = req.getParameter("membername");
		String loginUserName = util.getUserName(req);
		if (userNumber != null) {
			List<User> uList;
			try {
				uList = usrService.getUserListByUserNum(userNumber);
				model.put("memberlist", uList);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				return "user/search_member";
			} catch (UserNotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (userName != null) {
			List<User> uList;
			try {
				uList = usrService.getMListByName(userName);
				model.put("memberlist", uList);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				return "user/search_member";
			} catch (ResourceDefinitionInvalid e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.put("loginUserName", loginUserName);// to view user session name in home page
		return "user/search_member";
	}

	@RequestMapping(value = "/managemember/{memberNum}", method = RequestMethod.GET)
	public String updateMember(@PathVariable("memberNum") String memberNum, ModelMap model, HttpServletRequest req) {

		String authAdminResult = util.authenticateAdmin(req, model);
		String loginUserName = util.getUserName(req);
		if (authAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			User user = new User();
			user.setPassword("Please enter a password");
			try {
				user = usrService.getUser(memberNum);
				user.setPassword("Please enter a password");
				model.put("title", usrService.getTitleList());
				model.put("user", user);
				model.put("loginUserName", loginUserName);// to view user session name in home page
				return "user/managemember";
			} catch (UserNotFound e) {
				return "user/search_member";
			}
		} else
			model.put("loginUserName", loginUserName);// to view user session name in home page
		return authAdminResult;
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST)
	public String updateMember(ModelMap model, User user, HttpServletRequest req) {
		// Retrieves userNumber from session
		String userNumber = user.getUserNumber();
		user.setPassword("Enter Password");
		User existinguser = new User();

		try {
			existinguser = usrService.getUser(userNumber);
		} catch (UserNotFound e1) {

			return "user/managemember";
		}

		System.out.println(existinguser.toString());

		existinguser.setTitle(user.getTitle());
		existinguser.setFirstName(user.getFirstName());
		existinguser.setLastName(user.getLastName());
		existinguser.setMiddleName(user.getMiddleName());
		existinguser.setEmailAddress(user.getEmailAddress());
		existinguser.setPhoneNumber(user.getPhoneNumber());

		// Validation Using UserValidator Class
		// Perform validation Using UserValidator Class
		// Create UserValidator
		UserValidator uservalidator = new UserValidator();

		// Create DataBinder to Bind UserValidator
		DataBinder binder = new DataBinder(existinguser);
		binder.setValidator(uservalidator);

		// Validate the Data
		binder.validate();

		// Check Results
		System.out.println(existinguser.toString());
		BindingResult results = binder.getBindingResult();
		System.out.println(user.toString());
		System.out.println(results.toString());
		if (results.hasErrors()) {
			// Provide feedback to user that an error has occurred and what are the actions
			// that can be taken
			return "user/managemember"; // default redirection for guest/user to retry
		} else {
			// This means that there is no validation error
			try {
				// throws UserNotFound : this is to catch is the user object passed to the
				usrService.updateUser(existinguser);
				List<User> uList;
				uList = usrService.getUserListByUserNum(existinguser.getUserNumber());
				model.put("memberlist", uList);

				// addUser method is null
				System.out.println(existinguser.toString());
				return "user/search_member"; // This means that sign-up success
			} catch (UserNotFound e) {
				System.out.println(e.getMessage());

			}
		}
		return "user/search_member"; // default redirection for guest/user to retry
	}

	@RequestMapping(value = "/expiremember/{memberNum}", method = RequestMethod.GET)
	public String expireMember(ModelMap model, @PathVariable(required = true, name = "memberNum") String memberNum) {
		User user = new User();
		List<User> uList = null;
		try {

			user = usrService.getUser(memberNum);
			// String username = user.getFirstName();
			usrService.removeUser(memberNum);
			eMailService.notifyExpireUser(user);
			uList = usrService.getMListByName(user.getFirstName());

		} catch (UserNotFound e) {

		} catch (ResourceDefinitionInvalid e) {
			// TODO Auto-generated catch block
			return "user/search_member";
		}
		model.put("memberlist", uList);
		return "user/search_member";
	}

	@GetMapping("/return_home")
	public String ReturnHome(ModelMap model, HttpServletRequest req) {
		// Authenticate User
		String authResult = util.authenticateUser(req, model);
		String loginUserName = util.getUserName(req);
		if (authResult.equals("NG"))
			return "user/login";
		else
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return authResult;
	}

	@ModelAttribute("user")
	public User user() {
		return new User();
	}

}
