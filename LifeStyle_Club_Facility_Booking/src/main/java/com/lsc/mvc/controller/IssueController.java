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
@RequestMapping("/issue")
public class IssueController {

@Autowired
	private AuthenticateUser util; 

	@GetMapping
	public String get(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "home/admin_home";
		else return authResult;
	}

	@GetMapping("/manage-issue")
	public String ManageIssue(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "issue/manage_issue";
		else return "user/login";
	}
	
	@GetMapping("/search-issue")
	public String IssueSearch(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "issue/search_issue";
		else return "user/login";
	}
	
	@PostMapping("/issue-cancel")
	public String IssueCancel(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) return "home/admin_home";
		else return "user/login";
	}
	
//	@ModelAttribute("user")
//	public User user() { return new User(); }
}
