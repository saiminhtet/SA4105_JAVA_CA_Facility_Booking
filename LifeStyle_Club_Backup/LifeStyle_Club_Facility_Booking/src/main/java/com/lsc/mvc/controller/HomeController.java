package com.lsc.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
	public String testJspView() {

		return "jsp_test";
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "home/member_home";
	}

	@GetMapping("changepassword")
	public String CurrentPW(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "user/changepassword";
	}

	@GetMapping("user")
	public String User(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "user/signup";
	}

	@GetMapping("profile")
	public String Profile(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "user/profile";
	}

	@GetMapping("member")
	public String Member(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "home/member_home.html";
	}

	@GetMapping("admin")
	public String Admin(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "home/admin_home";
	}

	@GetMapping("superadmin")
	public String SuperAdmin(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "home/super_admin_home";
	}
	
	@GetMapping("addfacility")
	public String add_facility(Model model) {
		model.addAttribute("message", "Hello Spring MVC");
		return "facility/add_facility";
	}

}
