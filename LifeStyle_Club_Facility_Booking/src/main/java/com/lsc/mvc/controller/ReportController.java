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
import com.lsc.mvc.javabeans.AuthenticateUser;

@Controller
@RequestMapping("/viewreports")
public class ReportController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private AuthenticateUser util; 

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "report/main";
		else return authResult;
	}

	@GetMapping("/main")
	public String Main(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "report/main";
		else return "user/login";
	}
	
	@GetMapping("/facility-listing")
	public String FacilityListing(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "report/facility_listing";
		else return "user/login";
	}

	@ModelAttribute("user")
	public User user() { return new User(); }
}
