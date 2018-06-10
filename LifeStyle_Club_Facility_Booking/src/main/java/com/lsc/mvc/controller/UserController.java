package com.lsc.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lsc.mvc.model.User;
import com.lsc.mvc.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {

	
	@Autowired
	private UserRepository repo;
	
	
	@GetMapping
	public void get(ModelMap model) {
		
	}
	
	@GetMapping("profile")
	public String getAllMembers(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		// Retrieves userNumber from session
				HttpSession session = req.getSession();
				String userNumber = (String)session.getAttribute("userNumber");
				
				// Sets userNumber to default value if null
				if (userNumber == null) {
					userNumber = "A0002";
					session.setAttribute("userNumber", userNumber);
				}
		model.put("user", repo.getUserByUserNumber(userNumber));
		return "user/profile";
	}
	
	@ModelAttribute("user")
	public User userobject() {
		return new User();
	}
	
	
	
}
