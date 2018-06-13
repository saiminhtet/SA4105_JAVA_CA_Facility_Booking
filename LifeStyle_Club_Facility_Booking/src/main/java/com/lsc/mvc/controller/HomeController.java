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
	
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		// get the session variable
		HttpSession session = req.getSession();
		String loginUserName = (String) session.getAttribute("userName");
		if (authAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/admin_home";
		}
		if (authSuperAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/super_admin_home";
		}
		if (authMemberResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/member_home";
		}
		return "home/login";
	}

	@GetMapping("/login")
	public String Login(HttpServletRequest req, ModelMap model) {

		// Setting up variable to set attributes in view
		String authAdminResult = util.authenticateAdmin(req, model);
		String authSuperAdminResult = util.authenticateSuperAdmin(req, model);
		String authMemberResult = util.authenticateMember(req, model);

		// get the session variable
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
		if (authMemberResult.equals("OK")) {
			model.put("loginUserName", loginUserName);
			return "home/member_home";
		}
		return "home/login";
	}

	@PostMapping("/login")
	public String validate_Login(HttpServletRequest req, HttpSession session, ModelMap model) {

		User user = new User();

		String login_userNumber = req.getParameter("usernumber");
		String login_password = req.getParameter("password");

		try {
			user = usrService.validateLogin(login_userNumber, login_password);

		} catch (UserNotFound e) {
			
			model.put("error", "Login Failed!");
			return "home/login";
		}

		// Save user info in the session
		String userNumber = user.getUserNumber();
		String userName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
		session.setAttribute("userNumber", userNumber);
		session.setAttribute("userName", userName);

		System.out.println(user.toString());
		System.out.println(userNumber);
		System.out.println(userName);
		System.out.println(session.getAttribute("userNumber"));
		System.out.println(session.getAttribute("userName"));

		String authencate_userlogin = util.authenticateUser(req, model);// redirection for user to retry
		model.put("loginUserName", userName);
		return authencate_userlogin; // This means that login success // default

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
		String loginUserName = (String) session.getAttribute("userName");// get the session userName
		String authMemberResult = util.authenticateMember(req, model);
		if (authMemberResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/member_home";
		} else if (loginUserName.equals(null))
			return "home/login";
		else
			model.put("loginUserName", loginUserName);
		return authMemberResult;
	}

	@GetMapping("/admin")
	public String Admin(ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginUserName = (String) session.getAttribute("userName");// get the session userName
		String authAdminResult = util.authenticateAdmin(req, model);
		if (authAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/admin_home";
		} else if (loginUserName.equals(null))
			return "home/login";
		else
			model.put("loginUserName", loginUserName);
		return authAdminResult;
	}

	@GetMapping("/superadmin")
	public String SuperAdmin(ModelMap model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginUserName = (String) session.getAttribute("userName");// get the session userName
		String authSupAdminResult = util.authenticateSuperAdmin(req, model);
		if (authSupAdminResult.equals("OK")) {
			model.put("loginUserName", loginUserName);// to view user session name in home page
			return "home/super_admin_home";
		} else if (loginUserName.equals(null))
			return "home/login";
		else
			model.put("loginUserName", loginUserName);
		return authSupAdminResult;

	}



}
