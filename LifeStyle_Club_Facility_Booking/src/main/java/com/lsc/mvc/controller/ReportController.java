package com.lsc.mvc.controller;

import java.time.LocalDate;

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

import com.lsc.mvc.exception.BookingNotFound;
import com.lsc.mvc.exception.FacilityNotFound;
import com.lsc.mvc.exception.ResourceDefinitionInvalid;
import com.lsc.mvc.exception.UserNotFound;
import com.lsc.mvc.model.User;
import com.lsc.mvc.service.BookingService;
import com.lsc.mvc.service.FacilityService;
import com.lsc.mvc.service.UserService;
import com.lsc.mvc.validator.UserValidator;
import com.lsc.mvc.javabeans.AuthenticateUser;

@Controller
@RequestMapping("/view-reports")
public class ReportController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private FacilityService fService;
	
	@Autowired
	private BookingService bService;
	
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
		if (authResult.equals("OK")) {
			model.addAttribute("facilityListing", fService.getFacilityList());
			return "report/facility_listing";
		}
		else return "user/login";
	}
	
	@GetMapping("/admin-listing")
	public String AdminListing(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			model.addAttribute("adminListing", uService.getAList());
			return "report/admin_listing";
		}
		else return "user/login";
	}
	
	@GetMapping("/member-listing")
	public String MemberListing(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			model.addAttribute("memberListing", uService.getMList());
			return "report/member_listing";
		}
		else return "user/login";
	}
	
	@GetMapping("/facility-usage")
	public String FacilityUsage(HttpServletRequest req, ModelMap model) {
		// Authenticate User
		String authResult = util.authenticateAdmin(req, model);
		if (authResult.equals("OK")) {
			try {
				model.addAttribute("fUsageListing", bService.getFacilityUsageListByFacilityListAndDate(fService.getFacilityList(), LocalDate.now().minusDays(6), LocalDate.now()));
				return "report/facility_usage";
			} catch (FacilityNotFound e) { return "report/main";
			} catch (BookingNotFound e) { return "report/main"; }
		}
		else return "user/login";
	}
}
